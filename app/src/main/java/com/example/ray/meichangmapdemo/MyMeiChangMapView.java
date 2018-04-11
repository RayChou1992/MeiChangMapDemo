package com.example.ray.meichangmapdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyMeiChangMapView extends View {
    MeiChangMapBean mapBean;
    Context context;
    Resources r;
    Bitmap bmp, bmp_doulj, bmp_doulj_yb, bmp_ylj;
    int bg_width, bg_height;


    //为使图片居中，导致的高度差。
    int distanceHeight;

    //图片的偏转角度
    double angle, meidAngle, meidAngle1, jspAngle;
    //汽车沟左偏移
    double carLeft;
    //火车沟左偏移
    double TrainLeft;

    //分辨率系数
    int fenbianlvNum;

    int isMultiple = 0;
    boolean isTurn = false;
    Handler handler;
    //标识是否已经获取了宽高
    boolean haveMeasured = false;


    public MyMeiChangMapView(Context context) {
        super(context);
    }

    @SuppressLint("ResourceType")
    public MyMeiChangMapView(Context context, Handler handler) {
        super(context);
        this.mapBean = mapBean;
        this.context = context;
        this.handler = handler;
        initBitMap();
    }

    private void initBitMap() {
        r = this.getContext().getResources();
        bmp = BitmapFactory.decodeResource(r, R.mipmap.dit);
        bg_width = bmp.getWidth();
        bg_height = bmp.getHeight();
        Log.e("tag", "底图的宽高为：" + bg_width + "," + bg_height);
        fenbianlvNum = bg_height / 239;

        if ((isMultiple == 14 || isTurn) && isMultiple > 1) {
            isTurn = true;
            isMultiple--;
        } else if (isMultiple == 1) {
            isTurn = false;
            isMultiple++;
        } else {
            isMultiple++;
        }

    }

    public void setData(MeiChangMapBean meiChangMapBean) {
        this.mapBean = meiChangMapBean;
        haveMeasured = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        if (!haveMeasured) {
            drawBG(canvas, p);
        } else {
            drawBG(canvas, p);
            drawYard(canvas, p, mapBean.getMc());
        }


    }

    /**
     * 绘制煤场图BG底图
     *
     * @param canvas
     */
    private void drawBG(Canvas canvas, Paint p) {


        int cvsWidth = canvas.getWidth();
        int cvsHeight = canvas.getHeight();

        //此处默认煤场图的高<画布的高（PC端），如果有改动，再次之前，要计算一次宽高以适配当前屏幕
        Log.e("tag", "画布的宽高为：" + canvas.getWidth() + "," + canvas.getHeight());
        /**
         * (cvsHeight - bg_height) / 2 + bg_height)，是为了图像能到垂直居中
         */
        distanceHeight = (cvsHeight - bg_height) / 2;

        Log.e("tag", distanceHeight + "高度差");
        canvas.drawBitmap(bmp, new Rect(0, 0, bg_width, bg_height), new Rect(0, distanceHeight, cvsWidth, distanceHeight + bg_height), p);
        Log.e("tag", "最终的宽高为：" + canvas.getWidth() + "," + ((canvas.getHeight() - bg_height) / 2 + bg_height));

        if (!haveMeasured) {
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = new MeiChangMapParameter(bg_width, bg_height, distanceHeight, cvsWidth, cvsHeight);
            handler.sendMessage(msg);

        }


    }


    /**
     * 绘制煤场图和煤堆及其名称
     * <p>
     * 1、获取煤场图时，需要传递地图的宽高，约定了一个算法：
     * width=  canvas.width - ((canvas.width - translateX) * 0.3934681181959565) - translateX - ((canvas.width - translateX) * 0.0139968895800933)（此处如果宽度*系数，则第三步中的不再需要）
     * height= canvas.height和底图的height中较小值
     * <p>
     * 2、绘制煤场图时，需要将地图整体下移Y值，使其垂直居中显示
     * <p>
     * 3、在画各煤堆时，需要将得到的宽度*系数，这个系数=（1-0.3934681181959565-0.0139968895800933）=0.5925349922239502;(与第1步中的宽度*系数，只需要有一个即可)
     * <p>
     * 4、在画各煤堆时，需要将得到的高度+第2步中的Y值，即为：(canvas.getHeight() - bg_height) / 2
     * <p>
     * 以上算法，对之后的各种绘制，都有效
     *
     * @param canvas
     * @param mcBean
     */
    private void drawYard(Canvas canvas, Paint p, MeiChangMapBean.McBean mcBean) {
        //偏移角度
        angle = Math.atan((canvas.getWidth() * 0.3934681181959565 - canvas.getWidth() * 0.0388802488335925) / (bg_height)) / Math.PI * 180;
        //煤堆偏移的角度
        meidAngle = Math.tan(angle * 0.017453292519943);
        //斗轮机偏移角度，


        meidAngle1 = Math.tan(Math.atan((canvas.getWidth() * 0.3934681181959565 - canvas.getWidth() * 0.0388802488335925) / (bg_height / fenbianlvNum)) / Math.PI * 180 * 0.017453292519943);
        jspAngle = Math.tan((90 - Math.atan((canvas.getWidth() * 0.3934681181959565 - canvas.getWidth() * 0.0388802488335925) / (bg_height / 2)) / Math.PI * 180) * 0.017453292519943);
        Log.e("tag", "角度：" + angle + "," + meidAngle);

        double total = Double.valueOf(mapBean.getCoalData().get一期());
        List<MeiChangMapBean.MdListBean> mdLists = mapBean.getMdList();


        for (MeiChangMapBean.MdListBean mdListBean : mdLists) {
            double tXa = mdLists.get(0).getXD() + (mcBean.getYD() - mdLists.get(0).getYD()) * meidAngle;
            double tYa = mdLists.get(0).getYD();
            double tXd = mdLists.get(0).getXU() + (mcBean.getYD() - mdLists.get(0).getYU()) * meidAngle;
            double tYd = mdLists.get(0).getYU();
            double h = mdListBean.getAmount() / (((tXd - tXa) * (tYa - tYd)) * (total / (mcBean.getYD() * mcBean.getXU() * 5)));

            //这里图片的宽度本身为643，1080的分辨率下是1286，但此处减法都需要减去643.如果图片宽度变化，也需要改变

            draw3D(Math.abs(canvas.getWidth() - 643) + mdListBean.getXD(), mdListBean.getYD() + distanceHeight,
                    Math.abs(canvas.getWidth() - 643) + mdListBean.getXR(), mdListBean.getYR() + distanceHeight,
                    Math.abs(canvas.getWidth() - 643) + mdListBean.getXU(), mdListBean.getYU() + distanceHeight,
                    Math.abs(canvas.getWidth() - 643) + mdListBean.getXL(), mdListBean.getYL() + distanceHeight,
                    h, meidAngle, mcBean, mdListBean, canvas
            );
        }

        drawDouLJ(canvas, p, mapBean.getDoulj(), mcBean);
        drawYelj(canvas, p, mapBean.getYlList(), mcBean);
    }


    /**
     * 绘制煤堆
     *
     * @param Xa
     * @param Ya
     * @param Xb
     * @param Yb
     * @param Xc
     * @param Yc
     * @param Xd
     * @param Yd
     * @param h
     * @param meidAngle
     * @param mcBean
     * @param mdListBean
     * @param canvas
     */
    private void draw3D(double Xa, double Ya, double Xb, double Yb, double Xc, double Yc, double Xd, double Yd, double h, double meidAngle, MeiChangMapBean.McBean mcBean, MeiChangMapBean.MdListBean mdListBean, Canvas canvas) {
        carLeft = canvas.getWidth() * 0.0388802488335925;
        TrainLeft = canvas.getWidth() * 0.3934681181959565;
        Log.e("tag", "火车沟left" + TrainLeft);
        //计算顶面4个点的坐标
        double dXa = Xa + h * 1 / Math.tan(70 * 0.017453292519943);
        double dYa = Ya - h * 1 / Math.tan(70 * 0.017453292519943);
        double dXb = Xb - h * 1 / Math.tan(70 * 0.017453292519943);
        double dYb = Yb - h * 1 / Math.tan(70 * 0.017453292519943);
        double dXc = Xc - h * 1 / Math.tan(70 * 0.017453292519943);
        double dYc = Yc + h * 1 / Math.tan(70 * 0.017453292519943);
        double dXd = Xd + h * 1 / Math.tan(70 * 0.017453292519943);
        double dYd = Yd + h * 1 / Math.tan(70 * 0.017453292519943);
        //底角四点坐标
        double tXa = Xa + (mcBean.getYD() - Ya) * meidAngle + carLeft;
        double tYa = Ya;

        double tXb = Xb + (mcBean.getYD() - Yb) * meidAngle + carLeft;
        double tYb = Yb;

        double tXc = Xc + (mcBean.getYD() - Yc) * meidAngle + carLeft;
        double tYc = Yc;

        double tXd = Xd + (mcBean.getYD() - Yd) * meidAngle + carLeft;
        double tYd = Yd;

        //顶角四点坐标
        double tdXa = dXa + (mcBean.getYD() - dYa) * meidAngle + carLeft;
        double tdYa = dYa - h;

        double tdXb = dXb + (mcBean.getYD() - dYb) * meidAngle + carLeft;
        double tdYb = dYb - h;

        double tdXc = dXc + (mcBean.getYD() - dYc) * meidAngle + carLeft;
        double tdYc = dYc - h;

        double tdXd = dXd + (mcBean.getYD() - dYd) * meidAngle + carLeft;
        double tdYd = dYd - h;
        Paint p = new Paint();
        Path path = new Path();
        //连接右侧面
        path.moveTo((float) (tXb), (float) tYb);
        path.lineTo((float) (tdXb), (float) tdYb);
        path.lineTo((float) (tdXc), (float) tdYc);
        path.lineTo((float) (tXc), (float) tYc);
        path.lineTo((float) (tXb), (float) tYb);
        //连接内上侧面
        path.moveTo((float) (tXc), (float) tYc);
        path.lineTo((float) (tdXc), (float) tdYc);
        path.lineTo((float) (tdXd), (float) tdYd);
        path.lineTo((float) (tXd), (float) tYd);
        path.lineTo((float) (tXc), (float) tYc);
        //连接下侧面
        path.moveTo((float) (tXa), (float) tYa);
        path.lineTo((float) (tdXa), (float) tdYa);
        path.lineTo((float) (tdXb), (float) tdYb);
        path.lineTo((float) (tXb), (float) tYb);
        path.lineTo((float) (tXa), (float) tYa);
        //连接顶角各点
        path.moveTo((float) (tdXa), (float) tdYa);
        path.lineTo((float) (tdXb), (float) tdYb);
        path.lineTo((float) (tdXc), (float) tdYc);
        path.lineTo((float) (tdXd), (float) tdYd);
        path.lineTo((float) (tdXa), (float) tdYa);

        canvas.drawPath(path, p);

        //绘制煤堆名称
        float x = (float) (tdXb - ((tdXb - tdXa) / 2));
        float y = (float) (tYa - 20);
        canvas.save();

        p.setColor(Color.RED);
        canvas.drawText(mdListBean.getName(), x, y, p);

        canvas.restore();


    }


    /**
     * 绘制斗轮机
     *
     * @param canvas
     * @param douljs
     * @param mcBean
     */
    private void drawDouLJ(Canvas canvas, Paint p, List<MeiChangMapBean.DouljBean> douljs, MeiChangMapBean.McBean mcBean) {
        for (MeiChangMapBean.DouljBean doulj : douljs) {
            if (doulj.getCourse() != null) {
                bmp_doulj = getImageFromAssetsFile("home/doulj/dlj_ic.png");
                bmp_doulj_yb = getImageFromAssetsFile("home/doulj/yb.png");
            } else {
                bmp_doulj = getImageFromAssetsFile("home/doulj/dlj_h.png");
                bmp_doulj_yb = getImageFromAssetsFile("home/doulj/yb_h.png");

            }
//            if (doulj.getCourse() != null) {
//                bmp_doulj = BitmapFactory.decodeResource(r, R.mipmap.dlj_ic);
//                bmp_doulj_yb = BitmapFactory.decodeResource(r, R.mipmap.yb);
//            } else {
//                bmp_doulj = BitmapFactory.decodeResource(r, R.mipmap.dlj_h);
//                bmp_doulj_yb = BitmapFactory.decodeResource(r, R.mipmap.yb_h);
//
//            }

            //2是不同屏幕分辨率对于像素点的一个关系，1080的分辨率下，屏幕宽度=图片宽度*2，在计算此处X时，需要还原，故需要除以meiAndle1
            double dX = doulj.getLng() + (mcBean.getYD() - doulj.getLat()) * meidAngle1 + carLeft;  // angle x
            double dY = doulj.getLat();          // angle y


            float dlj_left = (float) ((dX - (canvas.getWidth() + Math.abs(canvas.getWidth() - 643)) * 0.0241581259150806) * 0.5925349922239502);
            float dlj_top = (float) (dY - canvas.getHeight() * 0.0286458333333333) + distanceHeight + bmp_doulj.getHeight() / 2;
            canvas.drawBitmap(bmp_doulj, dlj_left, dlj_top, p);


            //绘制斗轮机的摇臂
            double angle = (doulj.getCourseAngle() - 90);
            canvas.save();

            float yb_left = (float) ((dX - (canvas.getWidth() + Math.abs(canvas.getWidth() - 643)) * 0.0241581259150806) * 0.5925349922239502);
            float yb_top = (float) (dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight + bmp_doulj.getHeight() / 2;

            canvas.translate(yb_left, yb_top);
            if (doulj.getCourse() != null) {
                canvas.rotate((float) angle);
            }
            canvas.translate(-yb_left, -yb_top);
            canvas.drawBitmap(bmp_doulj_yb, yb_left, yb_top, p);

            canvas.restore();

        }

    }

    /**
     * 绘制叶轮机
     * 478和239的区别
     */
    private void drawYelj(Canvas canvas, Paint p, List<MeiChangMapBean.YlListBean> yeljs, MeiChangMapBean.McBean mcBean) {
        double yelCarWidth = (canvas.getWidth() + Math.abs(canvas.getWidth() - 643) - ((canvas.getWidth() + Math.abs(canvas.getWidth() - 643)) * 0.0388802488335925 + bg_height / jspAngle)) / 15;
        double yelTrainWidth = yelCarWidth;

        double yelCarHeight = yelCarWidth / jspAngle;
        double yelTrainHeight = yelCarHeight;
        Log.e("tag", "yelCarWidth=" + yelCarWidth + ",yelCarHeigh=t" + yelCarHeight);
        for (MeiChangMapBean.YlListBean yelj : yeljs) {
            if (yelj.getFLAG() == 1) {
                bmp_ylj = getImageFromAssetsFile("home/yelj/ylj_lv.png");
            } else {
                bmp_ylj = getImageFromAssetsFile("home/yelj/ylj_h.png");
            }

            String title = yelj.getNAME();
            if ("汽车沟".equals(yelj.getYARD())) {
                double width = yelCarWidth * 14 / yelj.getWIDTH() * yelj.getLOCATION_X();
                //此处的网页端代码没看懂，暂时搁置
                if (yelj.getFLAG() == 1) {
                    if ((width + isMultiple * 10) <= yelj.getWIDTH()) {
                        width = width + isMultiple * 10;
                    }
                }


                double height = (yelCarHeight * jspAngle / yelj.getHEIGHT()) * yelj.getLOCATION_Y();
                if (height > yelCarHeight / 2) {

//                    bmp_ylj.setWidth((int) yelCarWidth);
//                    bmp_ylj.setHeight((int) yelCarHeight / 2);
                    canvas.drawBitmap(bmp_ylj, (float) (width + carLeft), (float) (distanceHeight + bg_height - height), p);
                } else {
//                    bmp_ylj.setWidth((int) yelCarWidth);
//                    bmp_ylj.setHeight((int) yelCarHeight / 2);
                    canvas.drawBitmap(bmp_ylj, (float) (width + carLeft), (float) (yelCarHeight / 2 + distanceHeight + bg_height - height), p);

                }

            }
            if ("火车沟".equals(yelj.getYARD())) {
                double width = (yelTrainWidth * 14 / yelj.getWIDTH()) * yelj.getLOCATION_X();
                if (yelj.getFLAG() == 1) {
                    if ((width + isMultiple * 10) <= yelj.getWIDTH()) {
                        width = width + isMultiple * 10;
                    }
                }
                double height = (yelCarHeight * jspAngle / yelj.getHEIGHT()) * yelj.getLOCATION_Y();
                if (height > yelCarHeight / 2) {

//                    bmp_ylj.setWidth((int) yelCarWidth);
//                    bmp_ylj.setHeight((int) yelCarHeight / 2);
                    canvas.drawBitmap(bmp_ylj, (float) (width + TrainLeft), (float) (distanceHeight), p);
                } else {
//                    bmp_ylj.setWidth((int) yelCarWidth);
//                    bmp_ylj.setHeight((int) yelCarHeight / 2);
                    canvas.drawBitmap(bmp_ylj, (float) (width + TrainLeft), (float) (distanceHeight - height * 3 / 2), p);

                }
            }
        }

    }


    /**
     * 从Assets中读取图片
     */
    private Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }
}

package com.example.ray.meichangmapdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyMeiChangMapView extends View {
    MeiChangMapBean mapBean;
    Context context;
    Resources r;
    Bitmap bmp, bmp_doulj, bmp_doulj_yb, bmp_ylj;
    int bg_width, bg_height;
    Rect mSrcRect, mDestRect;

    //横向缩放比例
    double scaleWidth;
    //纵向缩放比例
    double scaleHeight;

    //为使图片居中，导致的高度差。
    int distanceHeight;

    //图片的偏转角度
    double angle, meidAngle, jspAngle;
    //左偏移
    double carLeft;


    public MyMeiChangMapView(Context context) {
        super(context);
    }

    public MyMeiChangMapView(Context context, MeiChangMapBean mapBean) {
        super(context);
        this.mapBean = mapBean;
        this.context = context;
        initBitMap();
    }

    private void initBitMap() {
        r = this.getContext().getResources();
        bmp = BitmapFactory.decodeResource(r, R.mipmap.dit);
        bg_width = bmp.getWidth();
        bg_height = bmp.getHeight();
        Log.e("tag", "底图的宽高为：" + bg_width + "," + bg_height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();


        drawBG(canvas, p);
        drawYard(canvas, p, mapBean.getMc());

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

        canvas.save();
        canvas.drawBitmap(bmp, new Rect(0, 0, bg_width, bg_height), new Rect(0, distanceHeight, cvsWidth, distanceHeight + bg_height), p);
        Log.e("tag", "最终的宽高为：" + canvas.getWidth() + "," + ((canvas.getHeight() - bg_height) / 2 + bg_height));
        canvas.restore();

        scaleWidth = (double) bg_width / (double) cvsWidth;
        scaleHeight = 1;
    }


    /**
     * 绘制煤场图和煤堆及其名称
     * <p>
     * 1、获取煤场图时，需要传递地图的宽高，约定了一个算法：
     * width=  canvas.width - ((canvas.width - translateX) * 0.3934681181959565) - translateX - ((canvas.width - translateX) * 0.0139968895800933)
     * height= canvas.height和底图的height中较小值
     * <p>
     * 2、绘制煤场图时，需要将地图整体下移Y值，使其垂直居中显示
     * <p>
     * 3、在画各煤堆时，需要将得到的宽度*系数，这个系数=（1-0.3934681181959565-0.0139968895800933）=0.5925349922239502;
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
        angle = Math.atan((canvas.getWidth() * 0.3934681181959565 - canvas.getWidth() * 0.0388802488335925) / bg_height) / Math.PI * 180;
        //煤堆偏移的角度
        meidAngle = Math.tan(angle * 0.017453292519943);
        jspAngle = Math.tan((90 - angle) * 0.017453292519943);
        Log.e("tag", "角度：" + angle + "," + meidAngle);

        double total = Double.valueOf(mapBean.getCoalData().get一期());
        List<MeiChangMapBean.MdListBean> mdLists = mapBean.getMdList();


        for (MeiChangMapBean.MdListBean mdListBean : mdLists) {
            double tXa = mdLists.get(0).getXD() + (mcBean.getYD() - mdLists.get(0).getYD()) * meidAngle;
            double tYa = mdLists.get(0).getYD();
            double tXd = mdLists.get(0).getXU() + (mcBean.getYD() - mdLists.get(0).getYU()) * meidAngle;
            double tYd = mdLists.get(0).getYU();
            double h = mdListBean.getAmount() / (((tXd - tXa) * (tYa - tYd)) * (total / (mcBean.getYD() * mcBean.getXU() * 5)));


            draw3D(bg_width - canvas.getWidth() + (mdListBean.getXD()) * 0.5925349922239502, mdListBean.getYD() + distanceHeight,
                    bg_width - canvas.getWidth() + (mdListBean.getXR()) * 0.5925349922239502, mdListBean.getYR() + distanceHeight,
                    bg_width - canvas.getWidth() + (mdListBean.getXU()) * 0.5925349922239502, mdListBean.getYU() + distanceHeight,
                    bg_width - canvas.getWidth() + (mdListBean.getXL()) * 0.5925349922239502, mdListBean.getYL() + distanceHeight,
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

        canvas.save();
        canvas.drawPath(path, p);

        //绘制煤堆名称
        float x = (float) (tdXb - ((tdXb - tdXa) / 2));
        float y = (float) (tYa - 20);

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

            double dX = doulj.getLng() + (mcBean.getYD() - doulj.getLat()) * meidAngle + carLeft;  // angle x
            double dY = doulj.getLat();                                  // angle y
//            dX = dX + translateX;
//            dY = dY + translateY;


            canvas.save();
            canvas.translate((float) dX, (float) dY);
            canvas.translate(-(float) dX, -(float) dY);

            canvas.drawBitmap(bmp_doulj,
                    (float) ((dX - canvas.getWidth() * 0.0241581259150805) * 0.5925349922239502),
                    (float) (dY - canvas.getHeight() * 0.0286458333333333) + distanceHeight,
                    p);
            canvas.restore();


            //绘制斗轮机的摇臂
            double angle = (doulj.getCourseAngle() - 90) * Math.PI / 180;
            canvas.save();
//            Matrix matrix = new Matrix();
//            matrix.postTranslate((float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502),
//                    (float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502));//步骤1
//            matrix.postRotate((float) angle);//步骤2
////            matrix.postTranslate((float) -((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502),
////
////                    (float) -(dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight);
//
//            canvas.drawBitmap(bmp_doulj_yb, matrix, p);//步骤4
//            matrix.reset();
//            canvas.translate((float) (float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502), (float) (dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight);
//            if (doulj.getCourse() != null) {
////                canvas.rotate((float) (doulj.getCourseAngle() - 90),(float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502),(float) (dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight);
//                canvas.rotate((float) angle);
//            }
//            canvas.translate(-(float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502), -(float) (dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight);
            canvas.drawBitmap(bmp_doulj_yb,
                    (float) ((dX - canvas.getWidth() * 0.0241581259150806) * 0.5925349922239502),
                    (float) (dY - canvas.getHeight() * 0.0095486111111112) + distanceHeight,
                    p);

            canvas.restore();

        }

    }

    /**
     * 绘制叶轮机
     */
    private void drawYelj(Canvas canvas, Paint p, List<MeiChangMapBean.YlListBean> yeljs, MeiChangMapBean.McBean mcBean) {
        double yelCarWidth = (canvas.getWidth() - (((canvas.getWidth()) * 0.0388802488335925) + (239) / jspAngle)) / 15;
        double yelCarHeight = yelCarWidth / jspAngle;
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
//                if (yelj.getFLAG()==1){
//                    if (width+)
//                }

                double height = (yelCarHeight * jspAngle / yelj.getHEIGHT()) * yelj.getLOCATION_Y();

                canvas.drawBitmap(bmp_ylj, (float) width, (float) height+distanceHeight/2, p);
            }
            if ("火车沟".equals(yelj.getYARD())) {

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

package com.tian.autoandroid;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by tian on 2019/1/4.
 */

public class AccessibilitySampleService extends AccessibilityService {

    private AccessibilityNodeInfo accessibilityNodeInfo;
    private AccessibilityNodeInfo mLoveButton;

    PackageManager packageManager;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.w(TAG,"connetc service!!!!!!!!!!!");
        packageManager = this.getPackageManager();
        // 获取启动app的intent
        Intent intent = packageManager.getLaunchIntentForPackage("com.tian.news");
        // 每次启动app应用时，但是以重新启动应用的形式打开
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // 跳转
        startActivity(intent);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.w(TAG,"Accessbility event!!!!!!!!!!!");
        String nowPackageName0 = event.getPackageName().toString();
        if(nowPackageName0.equals("com.tian.news")){
            AccessibilityNodeInfo mnode = getRootInActiveWindow();
            if(mnode != null){
                List <AccessibilityNodeInfo> nodeInfoList = mnode.findAccessibilityNodeInfosByViewId("com.tian.news:id/home_recycle");
                if(nodeInfoList != null && !nodeInfoList.isEmpty()){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.w(TAG,"TTTTT:"+nodeInfoList.get(0).getClassName().toString());
                    nodeInfoList.get(0).performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);

                    Log.w(TAG,"CCCCC:"+nodeInfoList.get(0).getChildCount());
                    AccessibilityNodeInfo pnode = nodeInfoList.get(0);
                    int mChildCount = pnode.getChildCount();
                    if(mChildCount > 2){
                        Log.w(TAG, "class name:" + pnode.getChild(mChildCount-1));
                        pnode.getChild(mChildCount-1).performAction(AccessibilityNodeInfo.ACTION_CLICK);

                        //文章浏览

                        for(int i = 0;i<3;i++){
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                        AccessibilityNodeInfo mnode1 = getRootInActiveWindow();
                        Log.w(TAG,"class:"+mnode1);
                        mnode1.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                        if(mnode1 != null){
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            List <AccessibilityNodeInfo> nodeInfoList1 = mnode1.findAccessibilityNodeInfosByViewId("com.tian.news:id/article_commment_recyclerview");
                            Log.w(TAG,"article class:"+nodeInfoList1);
                            if(nodeInfoList1 != null && !nodeInfoList1.isEmpty()){

                                Log.w(TAG,"TTTTT:"+nodeInfoList1.get(0).getClassName().toString());
                                for(int i = 0;i<5;i++){
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    nodeInfoList1.get(0).performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                                }
                                //返回列表页
                                performGlobalAction(GLOBAL_ACTION_BACK);
                            }else {
                                Log.w(TAG,"nodeInfoList1 null");
                            }
                        }else{
                            Log.w(TAG,"mnode1 null");
                        }
                    }

                }
            }
        }
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                String nowPackageName = event.getPackageName().toString();
                Log.w(TAG,nowPackageName);
                if(nowPackageName.equals("com.tian.news")){
//                      performGlobalAction(GLOBAL_ACTION_RECENTS);
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.w(TAG,"swipe event!!!!!!!!!!!");
//                        }
//                    }, 10000);
                }
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                String nowPackageName1 = event.getPackageName().toString();
                Log.w(TAG,nowPackageName1);
//                if(nowPackageName1.equals("com.tian.news")){
//                    AccessibilityNodeInfo mnode = getRootInActiveWindow();
//                    List <AccessibilityNodeInfo> listNodes = mnode.findAccessibilityNodeInfosByViewId("com.tian.news:id/home_recycle");
//                    Log.w(TAG,"TTTTT:"+listNodes.get(0).getClassName().toString());
//                    listNodes.get(0).performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//                }
                break;
        }
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
//        String nowPackageName = event.getPackageName().toString();
//        if(nowPackageName.equals("com.tian.news")){
//            // 判断是否为我们所需的窗口状态变化
//            if(event.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
//                performGlobalAction(AccessibilityService.GESTURE_SWIPE_DOWN);
//            }
//        }
//        Log.d(TAG, event.getPackageName().toString() + event.getEventType());
//        int eventType = event.getEventType();
//        switch (eventType) {
//            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
//                AccessibilityNodeInfo mNodeInfo = getRootInActiveWindow();
//                Rect rect = new Rect();
//                mNodeInfo.getBoundsInScreen(rect);
//                boolean isPerformed = mNodeInfo.performAction(GESTURE_SWIPE_RIGHT);
//                accessibilityNodeInfo = mNodeInfo;
//                traversalNodeInfo(mNodeInfo);
//                //模拟左滑
//                performGlobalAction(AccessibilityService.GESTURE_SWIPE_DOWN);
////                    performSwipeRight(mNodeInfo);
//                Log.d(TAG, "child counts is :" + mNodeInfo.getChildCount() + "\nrect is :" + rect.toString() + "\n performed is :" + isPerformed);
//                break;
//        }
    }

    @Override
    public void onInterrupt() {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean handle(AccessibilityNodeInfo info) {

        return true;
    }

    private void traversalNodeInfo(AccessibilityNodeInfo mNodeInfo) {
        int mChildCount = mNodeInfo.getChildCount();
        Log.w(TAG, "class name:" + mNodeInfo.getClassName().toString() + "  child count is :" + mChildCount + "\n");
        mLoveButton = mNodeInfo;
        if (mChildCount > 0) {
            for (int i = 0; i < mChildCount; i++) {
                traversalNodeInfo(mNodeInfo.getChild(i));
            }
        }
        Log.w(TAG,"=========Parent=========");
    }

    /**
     * 模拟上滑操作
     */
    public void performScrollForward() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
    }

    /**
     * 查找对应文本的View
     *
     * @param text      text
     * @param clickable 该View是否可以点击
     * @return View
     */
    public AccessibilityNodeInfo findViewByText(String text, boolean clickable) {
        AccessibilityNodeInfo accessibilityNodeInfo = getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return null;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null && (nodeInfo.isClickable() == clickable)) {
                    return nodeInfo;
                }
            }
        }
        return null;
    }
}

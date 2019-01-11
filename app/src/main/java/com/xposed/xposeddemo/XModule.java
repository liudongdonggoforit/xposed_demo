package com.xposed.xposeddemo;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XModule implements IXposedHookLoadPackage {

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.ss.android.ugc.aweme")){
            Class<?> clazz = null;
            try {
                clazz = lpparam.classLoader.loadClass("com.ss.android.common.applog.UserInfo");
                Log.i("ldd", "load class : " + clazz);
            } catch (Exception e){
                Log.i("ldd", "exception : " + e.getMessage());
                e.printStackTrace();
            }

            XposedHelpers.findAndHookMethod("com.ss.android.common.applog.UserInfo", lpparam.classLoader, "getUserInfo",
                    int.class, String.class, String[].class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            try {
                                StringBuilder sb = new StringBuilder();
                                String[] ary = (String[]) param.args[2];
                                if (ary != null) {
                                    for (int i = 0; i < ary.length; i++) {
                                        sb.append(ary[i]+":");
                                    }
                                }

                                if (((String)param.args[1]).contains("aweme/v1/feed")){
                                    Log.i("ldd","args0 : " + param.args[0]);
                                    Log.i("ldd","args1 : " + param.args[1]);
                                    Log.i("ldd","args2 : " + sb);
                                    Log.i("ldd","result : " + param.getResult());
                                }
                            }catch (Exception e){
                                Log.i("ldd", "afterHookedMethod exception : " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });
//            XposedHelpers.findAndHookMethod("com.xposed.xposeddemo.MainActivity", lpparam.classLoader, "isModuleActive", XC_MethodReplacement.returnConstant(true));
        }
    }
}
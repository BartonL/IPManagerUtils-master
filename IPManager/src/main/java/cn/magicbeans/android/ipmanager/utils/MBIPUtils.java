package cn.magicbeans.android.ipmanager.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import cn.magicbeans.android.ipmanager.db.MBIPDao;
import cn.magicbeans.android.ipmanager.module.MBIPInfo;

/**
 * Created by Administrator on 2015/10/12 0012.
 */
public class MBIPUtils {

    private static MBIPUtils ipUtils;

    private MBIPDao ipDao;


    public MBIPUtils(Context context) {
        ipDao = new MBIPDao(context);
    }

    public static MBIPUtils getInstance(Context context) {

        if (ipUtils == null) {
            ipUtils = new MBIPUtils(context);
        }
        return ipUtils;
    }

    /**
     * 添加IP
     *
     * @param info
     */
    public void insertIPPort(MBIPInfo info) {

        MBIPInfo defeault = ipDao.queryDefeaultIPInfo();
        if (defeault == null) {
            info.isDefeault = 1;
        }
        ipDao.insert(info);

    }

    /**
     * 设置默认IP
     *
     * @param info
     */
    public void setDefeaultIPPort(MBIPInfo info) {

//        if (info.isDefeault == 1) {
//            return;
//        }

//        MBIPInfo defeault = ipDao.queryDefeaultIPInfo();
        if (info != null) {
            ipDao.setDefeault(info);
        }
        ipDao.setDefeault(info);

    }

    /**
     * 设置非默认IP
     *
     * @param info
     */
    public void setNotDefeaultIPPort(MBIPInfo info) {

        if (info.isDefeault == 0) {
            return;
        }
        if (info != null) {
            ipDao.setDefeaultState(info);
        }
        ipDao.setDefeaultState(info);

    }

    /**
     * 删除IP
     *
     * @param info
     */
    public void deleteIPPort(MBIPInfo info) {
        ipDao.delete(info);
    }

    /**
     * 更新IP
     *
     * @param info
     */
    public void updateIPPort(MBIPInfo oldinfo, MBIPInfo info) {
        ipDao.update(oldinfo, info);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<MBIPInfo> queryData() {
        return ipDao.queryData();
    }


    /**
     * @param defeaultIP   默认IP
     * @param defeaultPort 默认端口
     * @return
     */
    public String getIPPort(String defeaultIP, String defeaultPort) {

        MBIPInfo info = ipDao.queryDefeaultIPInfo();
        if (info == null) {
            return defeaultIP + ":" + defeaultPort;
        }
        return info.ip + ":" + info.port;
    }

    /**
     * 获取默认IP，不传默认值
     *
     * @return
     */
    public String getIPPort() {
        MBIPInfo info = ipDao.queryDefeaultIPInfo();
        if (info == null) {
            return null;
        }
        return info.ip + ":" + info.port;
    }
}

package cn.com.demo.javaweb.snacks.dao;

import java.util.List;

import cn.com.demo.javaweb.snacks.po.PicturePO;

public interface IPictureDAO {
     public List<PicturePO> findPictures(int fdId, int picType);
}

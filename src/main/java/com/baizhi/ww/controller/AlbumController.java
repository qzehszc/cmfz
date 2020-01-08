package com.baizhi.ww.controller;

import com.baizhi.ww.entity.Album;
import com.baizhi.ww.service.AlbumService;
import com.baizhi.ww.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @RequestMapping("showAllAlbums")
    @ResponseBody
    public Map<String, Object> showAllAlbums(Integer page, Integer rows){
        Map<String, Object> map = albumService.queryAllByPage(page, rows);
        return map;
    }
    @RequestMapping("editAlbum")
    @ResponseBody
    // oper 标示 album 数据 id 删除的id
    public Map<String, Object> editAlbum(String oper,Album album,String[] id){
        Map<String, Object> map = new HashMap<>();
        System.out.println("...执行增删改操作...edit...");
        if ("add".equals(oper)){
            System.out.println("添加操作。。。执行");
            Album album1 = albumService.insert(album);
            map.put("albumId",album1.getId());
        } else if ("edit".equals(oper)){
            System.out.println("修改操作。。。执行");
            albumService.update(album);
        } else {
            System.out.println("删除操作(可批量删除)。。。执行");
            int i = albumService.deleteByIdList(Arrays.asList(id));
            System.out.println("删除了"+ i + "条数据");
        }
        return map;
    }
    @RequestMapping("uploadAlbum")
    public Map uploadAlbum(HttpSession session, MultipartFile cover, String albumId, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(cover, request, "/upload/albumImg/");
        Album album = new Album();
        album.setId(albumId);
        album.setCover(http);
        albumService.update(album);
        hashMap.put("status",200);
        return hashMap;
    }
}

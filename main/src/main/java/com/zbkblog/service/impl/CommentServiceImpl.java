package com.zbkblog.service.impl;

import com.zbkblog.dao.CommentDao;
import com.zbkblog.entity.Comment;
import com.zbkblog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public void deleteByDocId(Long docId) {
        commentDao.deleteByDocId(docId);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public List<Comment> findByDocId(Long docId) {
        return commentDao.findByDocId(docId);
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }
}

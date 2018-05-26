package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.Comment;
import com.ucbcba.demo.Entities.Restaurant;
import com.ucbcba.demo.Entities.User;

public interface CommentService {
    Iterable<Comment> listAllComments();

    void saveComment(Comment comment);

    Comment getComment(Integer id);

    void deleteComment(Integer id);

    boolean existsComment(User userId, Restaurant restaurantId);


}

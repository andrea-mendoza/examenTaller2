package com.ucbcba.demo.Controllers;


import com.ucbcba.demo.Entities.Comment;
import com.ucbcba.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    String save(Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/showRestaurant/" + comment.getRestaurant().getId();
    }
    @RequestMapping("/commentlike/{id}")
    String like(@PathVariable Integer id) {
        Comment comment = commentService.getComment(id);
        comment.setLikes(comment.getLikes() + 1);
        commentService.saveComment(comment);
        return "redirect:/showRestaurant/" + comment.getRestaurant().getId();
    }

    @RequestMapping("/commentdislike/{id}")
    String disLike(@PathVariable Integer id) {
        Comment comment = commentService.getComment(id);
        if(comment.getLikes() != 0){
            comment.setLikes(comment.getLikes() -1);
            commentService.saveComment(comment);
        }
        return "redirect:/showRestaurant/" + comment.getRestaurant().getId();
    }
}
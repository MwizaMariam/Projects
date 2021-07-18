package cultureLearning.cultureLearning.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Comment;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.services.impl.CommentServiceImplemantation;
import cultureLearning.cultureLearning.services.impl.UserServiceImplementation;

@VariableResolver(DelegatingVariableResolver.class)
public class CommentModel {
	@Autowired
private Comment comment;
	@Autowired
	private User user;
@WireVariable("comment")
private CommentServiceImplemantation service;
private ListModelList<User>listOfUsers;
 private ListModelList<Comment>listOfComment;
 @WireVariable("Service")
 private UserServiceImplementation userService;

@Init
public void initialization() {
comment=new Comment();
listOfComment=new ListModelList<Comment>(service.findAll());
listOfUsers=new ListModelList<User>(userService.findAllUser());
user=new User();

}


@Command("save")
@NotifyChange("listOfComment")
public void createComment() {
	try {
		User use=(User)Sessions.getCurrent().getAttribute("s_user");
		comment.setUser(use);
		service.createComment(comment);
		Clients.showNotification("Ibitekerezo byany byoherejwe neza!!");
		System.out.println(System.getenv("DB_PASSWORD"));
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
@Command("delete")
@NotifyChange("listOfComment")
public void deleteCommen(@BindingParam("del")Comment comment) {
	try {
		service.deleteComment(comment);
		Clients.showNotification("Comment deleted!!");
	} catch (Exception e) {
		// TODO: handle exception
		Clients.showNotification("fail to delete"+e.getMessage());
		e.printStackTrace();
	}
}


public Comment getComment() {
	return comment;
}
public void setComment(Comment comment) {
	this.comment = comment;
}
public CommentServiceImplemantation getService() {
	return service;
}
public void setService(CommentServiceImplemantation service) {
	this.service = service;
}
public ListModelList<Comment> getListOfComment() {
	return listOfComment;
}
public void setListOfComment(ListModelList<Comment> listOfComment) {
	this.listOfComment = listOfComment;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public ListModelList<User> getListOfUsers() {
	return listOfUsers;
}
public void setListOfUsers(ListModelList<User> listOfUsers) {
	this.listOfUsers = listOfUsers;
}
public UserServiceImplementation getUserService() {
	return userService;
}
public void setUserService(UserServiceImplementation userService) {
	this.userService = userService;
}


}

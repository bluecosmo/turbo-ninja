package org.wicketstuff.chat.channel.examples.pages;

import java.util.UUID;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;
import org.wicketstuff.chat.channel.examples.panels.ChatPanel;
import org.wicketstuff.chat.channel.examples.panels.WicketChatPanel;
import org.wicketstuff.chat.channel.examples.panels.notification.JqueryNotificationPanel;
import org.wicketstuff.chat.components.notifications.InvitationPanel;
import org.wicketstuff.chat.components.notifications.pnotify.PnotifyJsReference;
import org.wicketstuff.chat.model.ChatroomModel;

public class WicketTimerChatPanelPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public WicketTimerChatPanelPage(PageParameters parameters) {
		ChatroomModel model = new ChatroomModel();
		model.setChannel("chatroom3");
		model.setUser(UUID.randomUUID().toString());
		IModel<ChatroomModel> crModel = Model.of(model);
		add(new JqueryNotificationPanel("jqueryNotificationPanel"){
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisibilityAllowed(false);
			}
		});
		add(new InvitationPanel("notificationPanel", crModel, Duration.seconds(30)){
			private static final long serialVersionUID = 1L;

			protected IChannelService getChannelService() {		
				return ((WicketApplication) WicketApplication.get()).getTimerChannelService();
			}
			
		});
		add(new ChatPanel("chatPanel3", crModel));
		
		add(new WicketChatPanel("chatPanel", "chatroom1"));
		add(new WicketChatPanel("chatPanel2", "chatroom2"));
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		 response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));	
	    	WicketComponentUtils.renderHeaderResponse(response, this.getClass());	
	}

}

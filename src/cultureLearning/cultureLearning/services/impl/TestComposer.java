package cultureLearning.cultureLearning.services.impl;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class TestComposer  extends SelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * public void doAfterCompose(Component comp) throws Exception {
	 * super.doAfterCompose(comp);
	 * 
	 * }
	 * 
	 * public void onClick$btn(Event e) throws InterruptedException{
	 * Messagebox.show("Hi btn"); }
	 */
	@Listen(Events.ON_CLICK + "= a")
    public void handleUpload(MouseEvent e) {
        Fileupload.get(1, new EventListener<UploadEvent>() {
            public void onEvent(UploadEvent event) throws Exception {
                Media[] medias = event.getMedias();
                System.out.println("upload " +  medias[0].getName());
            }
        });
    }
	
}

package gwt.material.design.client.api;

import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.ui.MaterialToast;

import java.util.LinkedHashMap;
import java.util.Map;

import org.moxieapps.gwt.uploader.client.File;
import org.moxieapps.gwt.uploader.client.Uploader;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteEvent;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteHandler;
import org.moxieapps.gwt.uploader.client.events.FileDialogStartEvent;
import org.moxieapps.gwt.uploader.client.events.FileDialogStartHandler;
import org.moxieapps.gwt.uploader.client.events.FileQueueErrorEvent;
import org.moxieapps.gwt.uploader.client.events.FileQueueErrorHandler;
import org.moxieapps.gwt.uploader.client.events.FileQueuedEvent;
import org.moxieapps.gwt.uploader.client.events.FileQueuedHandler;
import org.moxieapps.gwt.uploader.client.events.UploadCompleteEvent;
import org.moxieapps.gwt.uploader.client.events.UploadCompleteHandler;
import org.moxieapps.gwt.uploader.client.events.UploadErrorEvent;
import org.moxieapps.gwt.uploader.client.events.UploadErrorHandler;
import org.moxieapps.gwt.uploader.client.events.UploadProgressEvent;
import org.moxieapps.gwt.uploader.client.events.UploadProgressHandler;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessEvent;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.ProgressBar;

public class MaterialFileUploadWidget extends Composite {

	private static FileUploadWidgetUiBinder uiBinder = GWT.create(FileUploadWidgetUiBinder.class);

	interface FileUploadWidgetUiBinder extends UiBinder<Widget, MaterialFileUploadWidget> {
	}
	
	@UiField HTMLPanel panel;
	@UiField FocusPanel dropArea;
	
	private String servletUrl;
	private String text;
	private  final Uploader uploader = new Uploader(); 
	private final Map<String, ProgressBar> progressBars = new LinkedHashMap<String, ProgressBar>();  
	
	public Map<String, ProgressBar> getProgressBars() {
		return progressBars;
	}


	public MaterialFileUploadWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		
	}



	public void initUpload() {  
        final Map<String, Image> cancelButtons = new LinkedHashMap<String, Image>();  
        uploader.setUploadURL(servletUrl) 
                .setButtonText(text)
                .setButtonCursor(Uploader.Cursor.HAND)  
                .setButtonAction(Uploader.ButtonAction.SELECT_FILES)  
                .setFileQueuedHandler(new FileQueuedHandler() {  
                    public boolean onFileQueued(final FileQueuedEvent fileQueuedEvent) {  
                        // Create a Progress Bar for this file  
                        final ProgressBar progressBar = new ProgressBar(0.0, 1.0, 0.0, new CancelProgressBarTextFormatter());  
                        progressBar.setTitle(fileQueuedEvent.getFile().getName());  
                        progressBar.setHeight("18px");  
                        progressBar.addStyleName(MaterialResources.INSTANCE.materialcss().progress());
                        progressBars.put(fileQueuedEvent.getFile().getId(), progressBar);  
  
                        // Add Cancel Button Image  
                        final Image cancelButton = new Image(MaterialResources.INSTANCE.ic_progress_cancel());  

                        cancelButton.addClickHandler(new ClickHandler() {  
                            public void onClick(ClickEvent event) {  
                                uploader.cancelUpload(fileQueuedEvent.getFile().getId(), false);  
                                progressBars.get(fileQueuedEvent.getFile().getId()).setProgress(-1.0d);  
                                cancelButton.removeFromParent();  
                            }  
                        });  
                        cancelButtons.put(fileQueuedEvent.getFile().getId(), cancelButton);  
  
                        // Add the Bar and Button to the interface  
                        HorizontalPanel progressBarAndButtonPanel = new HorizontalPanel();  
                        progressBarAndButtonPanel.add(progressBar);  
                        progressBarAndButtonPanel.add(cancelButton);  
                        panel.add(progressBarAndButtonPanel);  
  
                        return true;  
                    }  
                })  
                .setUploadProgressHandler(new UploadProgressHandler() {  
                    public boolean onUploadProgress(UploadProgressEvent uploadProgressEvent) {  
                        ProgressBar progressBar = progressBars.get(uploadProgressEvent.getFile().getId());  
                        progressBar.setProgress((double) uploadProgressEvent.getBytesComplete() / uploadProgressEvent.getBytesTotal());  
                        return true;  
                    }  
                })  
                .setUploadCompleteHandler(new UploadCompleteHandler() {  
                    public boolean onUploadComplete(UploadCompleteEvent uploadCompleteEvent) {  
                        cancelButtons.get(uploadCompleteEvent.getFile().getId()).removeFromParent();  
                        
                        uploader.startUpload();  
                        return true;  
                    }  
                })  
                .setFileDialogStartHandler(new FileDialogStartHandler() {  
                    public boolean onFileDialogStartEvent(FileDialogStartEvent fileDialogStartEvent) {  
                        if (uploader.getStats().getUploadsInProgress() <= 0) {  
                            progressBars.clear();  
                            cancelButtons.clear();  
                        }  
                        return true;  
                    }  
                })  
                .setFileDialogCompleteHandler(new FileDialogCompleteHandler() {  
                    public boolean onFileDialogComplete(FileDialogCompleteEvent fileDialogCompleteEvent) {  
                        if (fileDialogCompleteEvent.getTotalFilesInQueue() > 0) {  
                            if (uploader.getStats().getUploadsInProgress() <= 0) {  
                            	MaterialToast.alert("Completed");
                                uploader.startUpload();  
                            }  
                        }  
                        return true;  
                    }  
                })  
                .setFileQueueErrorHandler(new FileQueueErrorHandler() {  
                    public boolean onFileQueueError(FileQueueErrorEvent fileQueueErrorEvent) { 
                    	ProgressBar progressBar = progressBars.get(fileQueueErrorEvent.getFile().getId());  
                    	progressBar.removeFromParent();
                    	String msg = "Upload of file " + fileQueueErrorEvent.getFile().getName() + " failed due to [" +   fileQueueErrorEvent.getErrorCode().toString() + "]: " + fileQueueErrorEvent.getMessage();
                    	MaterialToast.alert(msg);
                    	return true;  
                    }  
                })  
                .setUploadErrorHandler(new UploadErrorHandler() {  
                    public boolean onUploadError(UploadErrorEvent uploadErrorEvent) {  
                    	ProgressBar progressBar = progressBars.get(uploadErrorEvent.getFile().getId());  
                    	progressBar.removeFromParent();
                        cancelButtons.get(uploadErrorEvent.getFile().getId()).removeFromParent();
                        String msg = "Upload of file " + uploadErrorEvent.getFile().getName() + " failed due to [" +    uploadErrorEvent.getErrorCode().toString() + "]: " + uploadErrorEvent.getMessage();
                        MaterialToast.alert(msg);
                        return true;  
                    }  
                }).setUploadSuccessHandler(new UploadSuccessHandler() {

        			@Override
        			public boolean onUploadSuccess(UploadSuccessEvent uploadSuccessEvent) {
        				File file = uploadSuccessEvent.getFile();
        				ProgressBar progressBar = progressBars.get(uploadSuccessEvent.getFile().getId());  
        				MaterialToast.alert(file.getName());
        				progressBar.removeFromParent();
        				return true;
        			}
        		});    
  
        uploader.addStyleName("btn blue waves-light waves-effect");

        panel.add(uploader);  
  
        if (Uploader.isAjaxUploadWithProgressEventsSupported()) {  
             
        	dropArea.addMouseOverHandler(new MouseOverHandler() {
				
				@Override
				public void onMouseOver(MouseOverEvent event) {
					// TODO Auto-generated method stub
					dropArea.getElement().getStyle().setZIndex(-1);
				}
			});
        	
        	dropArea.addMouseOutHandler(new MouseOutHandler() {
				
				@Override
				public void onMouseOut(MouseOutEvent event) {
					// TODO Auto-generated method stub
					dropArea.getElement().getStyle().setZIndex(0);
				}
			});
        	
        	
        	dropArea.addDragOverHandler(new DragOverHandler() {  
                public void onDragOver(DragOverEvent event) {  
                    if (!uploader.getButtonDisabled()) {  
                    	dropArea.getElement().getStyle().setOpacity(1);
                    	dropArea.getElement().getStyle().setZIndex(999);
                    }  
                }  
            });  
        	dropArea.addDragLeaveHandler(new DragLeaveHandler() {  
                public void onDragLeave(DragLeaveEvent event) {  
                	dropArea.getElement().getStyle().setOpacity(0);
                	dropArea.getElement().getStyle().setZIndex(0);
                }  
            });  
        	dropArea.addDropHandler(new DropHandler() {  
                public void onDrop(DropEvent event) {  
                	dropArea.getElement().getStyle().setOpacity(0);
                	dropArea.getElement().getStyle().setZIndex(0);
                    if (uploader.getStats().getUploadsInProgress() <= 0) { 
                        progressBars.clear();  
                        cancelButtons.clear();  
                    }  
  
                    uploader.addFilesToQueue(Uploader.getDroppedFiles(event.getNativeEvent()));  
                    event.preventDefault();  
                }  
            });  
            panel.add(dropArea);  
        }  
  
    }  
  
    protected class CancelProgressBarTextFormatter extends ProgressBar.TextFormatter {  
        @Override  
        protected String getText(ProgressBar bar, double curProgress) {  
            if (curProgress < 0) {  
                return "Cancelled";  
            }  
            return ((int) (100 * bar.getPercent())) + "%";  
        }  
    }

	public String getServletUrl() {
		return servletUrl;
	}

	public void setServletUrl(String servletUrl) {
		this.servletUrl = servletUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public Uploader getUploader() {
		return uploader;
	}


	public FocusPanel getDropArea() {
		return dropArea;
	}


	public void setDropArea(FocusPanel dropArea) {
		this.dropArea = dropArea;
	}


	public void setDisabled(boolean disabled) {
		if(disabled){
			uploader.addStyleName(MaterialResources.INSTANCE.materialcss().disabled());
			uploader.setButtonDisabled(true);
		}else{
			uploader.removeStyleName(MaterialResources.INSTANCE.materialcss().disabled());
			uploader.setButtonDisabled(false);
		}
	}

    
	
}

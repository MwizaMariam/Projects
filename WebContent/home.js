//var myIndex = 1;
//var slider = true;
//automaticSlide ();
//var timer=null;
//var currentSlide = 0;
//
//function automaticSlide() {
//  var i;
//  var x = document.getElementsByClassName("single_slider");
//  if(x.length==0){setTimeout(automaticSlide, 5000);return;}
//  if(slider){
//  for (i = 0; i < x.length; i++) {
//    x[i].style.display = "none";  
//  }
//  myIndex++;
//  if (myIndex > x.length) {myIndex = 1} 
//  if(myIndex < 0) {myIndex = x.length + myIndex + 1}
//  x[myIndex-1].style.display = "block";  
//  }
//  timer = setTimeout(automaticSlide, 5000); 
//  /*timer=setTimeout(automaticSlide(),3000);
//  clearTimeout(timer);
//  timer = setTimeout(showSlide, 5000);*/
//  
//}
//var slideIndex = 1;
//
//function plusSlides(n) {
//	automaticSlide(slideIndex += n);
//	}
//
//
//var playing = true;
//var pauseButton = document.getElementById('pause');
//
//function pauseSlideshow(){
//    pauseButton.innerHTML = 'Play';
//    playing = false;
//    clearInterval(slideInterval);
//}
//
//function playSlideshow(){
//    pauseButton.innerHTML = 'Pause';
//    playing = true;
//    slideInterval = setInterval(nextSlide,2000);
//}
//
//function pauseSlide() {
//	if(playing){
//		automaticSlide();
//		console.log("A");
//	} else {
//		clearTimeout(timer);
//	}
//	playing = !playing;
//	slider = !slider;
//}
//
////pauseButton.onclick = function(){
////    if(playing){ pauseSlideshow(); }
////    else{ playSlideshow(); }
////};
//function nextSlide() {
////    goToSlide(currentSlide+1);
//	if(timer!=null)clearTimeout(timer);
//	automaticSlide();
//}
//
//function previousSlide() {
////    goToSlide(currentSlide-1);
//	myIndex--;
//	myIndex--;
//	if(timer!=null)clearTimeout(timer);
//	automaticSlide();
//}
//
//function goToSlide(n) {
////    slides[currentSlide].className = 'single_slider';
////    currentSlide = (n+slides.length)%slides.length;
////    slides[currentSlide].className = 'slide showing';
//	  var x = document.getElementsByClassName("single_slider");
//	  if(x.length==0) return;
//	  for (i = 0; i < x.length; i++) {
//	    x[i].style.display = "none";  
//	  }
//	  if(n<0) x[x.length-1].style.display = "block";
//	  else if(n>=x.length) x[0].style.display = "block";
//	  else x[n].style.display = "block";
//}
//	var next = document.querySelectorAll('[data-id=next]');
//	var previous = document.querySelectorAll('[data-id=previous]');
//	var pause = document.querySelectorAll('[data-id=pause]');
//	next.forEach(el=>el.onclick=()=>nextSlide());
//	previous.forEach(el=>el.onclick=()=>previousSlide());
//	pause.forEach(el=>el.onclick=()=>pauseSlide());
//	

	


zk.afterMount(function() {
    //Upload Progress Monitor Sample 1
    zk.UPMSample1 = zk.$extends(zk.Object, {
        updated : null,
        $init : function(uplder, filenm) {
            this._uplder = uplder;
            var id = uplder.id;
            //Add message and progressmeter
            zk.Widget.$(jq("$flist")).appendChild(
                new zul.box.Hlayout({
                    id: id + "_layout",
                    spacing: "6px",
                    sclass: "UPMSample1",
                    children: [
                        new zul.wgt.Html({
                            content: ['<div class="msg"><span>', filenm,'</span></div>'].join("")
                        }),
                        new zul.wgt.Progressmeter({
                          id: id + "_pgs"
                    })]
                })  
            );
        },
        update : function(sent, total) {
            zk.Widget.$(jq('$' + this._uplder.id + '_pgs')).setValue(sent);
        },
        destroy : function() {
            var layout = jq('$' + this._uplder.id + "_layout");
            if (zk.ie) {
                zk.Widget.$(layout).detach();
            } else {
                layout.animate({ width: 1}, 500, function() {
                    zk.Widget.$(layout).detach();
                });
            }
        }
    });
     
    //Upload Progress Monitor Sample 2
    zk.UPMSample2 = zk.$extends(zk.Object, {
        updated : null,
        $init : function(uplder, filenm) {
            this._uplder = uplder;
            var id = uplder.id, 
                uri = '../widgets/effects/upload_effect/img/upload-loader.gif', 
                html = ['<div id="', id,'" class="UPMSample2">',
                        '<image class="float-left" src="' , uri, '"/>',
                        '<div class="float-left">&nbsp;&nbsp;&nbsp;FileName: ', filenm , '</div>',
                        '<div class="float-right">', msgzk.FILE_SIZE, 
                        '<span id="' , id, '-sent">0</span> of ',
                        '<span id="' , id, '-total">0</span>',
                        '<span id="' , id, '-percent"> (0%)</span>',
                        '</div><div class="clear"></div></div>'].join("");
            jq("$footer").append(html);
            this.viewer = jq('#' + id)[0];          
        },
        update : function(sent, total) {
            jq('#' + this._uplder.id + '-sent').html(Math.round((total / 1024) * sent / 100) + msgzk.KBYTES);
            if (!this.updated) {
                this.updated = true;
                jq('#' + this._uplder.id + '-total').html(Math.round(total / 1024) + msgzk.KBYTES);
            }
            jq('#' + this._uplder.id + '-percent').html(" (" +sent + "%" + ")");
        },
        destroy : function() {
            jq(this.viewer).slideUp(1000);
        }
    });
});





	
	
	/*var videos=document.querySelectorAll(".video");
	var currentVideo= 0;
	function playVideo(nextVideo){
		videos[currentVideo].pause();
		videos[currentVideo].style.display='none';
		currrentVideo=nextVideo;
		videos[currentVideo].style.display='block';
		videos[currentVideo].play();
		
		videos[currentVideo].addEventListener('ended', function(){
			if(currentVideo+2<videos.length){
				playVideo(currentVideo+1);
			}
			else
			{
				playVideo(0);
					
				}
			});
		}
	
	function videoclk(e){
		var i= Array.from(document.querySelector("#videos").children).indexof(e.target);
		playVideo(i);
	}
	playVideo(0);*/





/**
 * 
 */
(function(){
	var video = document.querySelector('video')
	
	var constraints = { video: true,
	audia:false}
	
	navigator.mediaDevices.getUserMedia(constraints).then(function(stream){video.srcObject = stream; video.play();}).catch(function(err){});
	
})();
function capture() { var canvas = document.getElementById('canvasID'); var video = document.querySelector('video'); var context = canvas.getContext('2d'); context.drawImage(video, 0, 0, canvas.width, canvas.height); };

function send() { var canvas = document.getElementById('canvasID'); var imageData = canvas.toDataURL(); var xmlhttp = new XMLHttpRequest(); xmlhttp.open("POST", "/article/receiver", true); xmlhttp.send(imageData); };


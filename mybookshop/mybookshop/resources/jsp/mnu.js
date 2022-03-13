<!--
function go(arg_ref) {
	location.href=arg_ref
}

function newImage(arg) {
	if (document.images) {
		rslt = new Image();
		rslt.src = arg;
		return rslt;
	}
}

function changeImagesArray(array) {
	if (document.images && (preloadFlag == true)) {
		for (var i=0; i<array.length; i+=2) {
			document[array[i]].src = array[i+1];
		}
	}
}

function changeImages() {
	changeImagesArray(changeImages.arguments);
}

function toggleImages() {
	for (var i=0; i<toggleImages.arguments.length; i+=2) {
		<!-- if (selected == toggleImages.arguments[i]) -->
		      changeImagesArray(toggleImages.arguments[i+1]);
	}
}

var selected = '';
var preloadFlag = false;
function preloadImages() {
	if (document.images) {
		novosti_2 = newImage("../graphics/novosti_2.gif");
		novosti_0 = newImage("../graphics/novosti_0.gif");
		
		bestseller_2 = newImage("../graphics/bestseller_2.gif");
		bestseller_0 = newImage("../graphics/bestseller_0.gif");

		novinki_2 = newImage("../graphics/novinki_2.gif");
		novinki_0 = newImage("../graphics/novinki_0.gif");
		
		rasprod_2 = newImage("../graphics/rasprod_2.gif");
		rasprod_0 = newImage("../graphics/rasprod_0.gif");
		
		zagruzka_2 = newImage("../graphics/zagruzka_2.gif");
		zagruzka_0 = newImage("../graphics/zagruzka_0.gif");
		
		help_2 = newImage("../graphics/help_2.gif");
		help_0 = newImage("../graphics/help_0.gif");
		
		kontakty_2 = newImage("../graphics/kontakty_2.gif");
		kontakty_0 = newImage("../graphics/kontakty_0.gif");
		
		korzina_2 = newImage("../graphics/korzina_2.gif");
		korzina_0 = newImage("../graphics/korzina_0.gif");


		preloadFlag = true;
	}
}





function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_changeProp(objName,x,theProp,theValue) { //v6.0
  var obj = MM_findObj(objName);
  if (obj && (theProp.indexOf("style.")==-1 || obj.style)){
    if (theValue == true || theValue == false)
      eval("obj."+theProp+"="+theValue);
    else eval("obj."+theProp+"='"+theValue+"'");
  }
}
//-->

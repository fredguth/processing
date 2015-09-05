// As seen here, we need to preload Images and Fonts.
//
// See http://processingjs.org/reference/preload/
// and http://processingjs.org/reference/font/
// for more information.

/* @pjs preload=" deadmau5.jpg "; */

HImage d;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

	for (int i = 0; i<100; i++){
		d = new HImage("deadmau5.jpg");
		d
			.size( (int)random(75,175) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;
		H.add(d);
	}

	H.drawStage();
}

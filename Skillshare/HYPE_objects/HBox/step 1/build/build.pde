HBox d;

void setup(){
	size(600,600,P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

	for (int i = 0; i<100; i++){
		d = new HBox();
		d
			.depth( (int)random(25,125) )
			.width( (int)random(25,125) )
			.height( (int)random(25,125) )
			.strokeWeight(1)
			.stroke(#FF3300)
			.fill(#111111)
			.loc( (int)random(width), (int)random(height), (int)random(-100,100) )
			.anchorAt(H.CENTER)
		;

		H.add(d);
	}

	H.drawStage();
}

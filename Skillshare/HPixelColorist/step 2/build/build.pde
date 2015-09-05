HRect d;
HPixelColorist colors;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

	colors = new HPixelColorist("kelp.jpg").fillOnly();

	for (int i = 0; i<100; i++){
		d = new HRect();
		d
			.strokeWeight(1)
			.stroke(#000000)
			.size( (int)random(25,125) )
			.rotate( (int)random(360) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;
		colors.applyColor(d);
		H.add(d);
	}

	H.drawStage();
}

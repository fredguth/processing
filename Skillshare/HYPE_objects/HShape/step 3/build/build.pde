HShape d;

void setup(){
	size(600,600);
	H.init(this).background(#FFFFFF);
	smooth();

	for (int i = 0; i<15; i++){
		d = new HShape("vectors.svg");
		d
			.rotation( (int)random(360) )
			.size( (int)random(200,400) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;
		H.add(d);
	}

	H.drawStage();
}

HShape d;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

	for (int i = 0; i<25; i++){
		d = new HShape("vectors.svg");
		d
			.enableStyle(false)
			.strokeWeight(2)
			.stroke(#111111)
			.fill(#FF3300)
			.size( (int)random(150,300) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;
		H.add(d);
	}

	H.drawStage();
}

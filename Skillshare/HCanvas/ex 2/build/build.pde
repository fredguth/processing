HRect r1;

void setup() {
	size(640,640);
	H.init(this).background(#202020).autoClear(false);
	smooth();

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchor(50,125)
		.loc(width/2,height/2)
		.noStroke()
		.fill(#FF3300)
	;

	new HRotate(r1, 2);
}

void draw() {
	fill(#202020,10);
	rect(0,0,width,height);

	H.drawStage();
}


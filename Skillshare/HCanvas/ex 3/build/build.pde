HCanvas c1;
HRect r1;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	c1 = new HCanvas().autoClear(false).fade(4);
	H.add(c1);

	r1 = new HRect(100).rounding(10);
	c1.add(r1)
		.anchor(50,125)
		.loc(width/2,height/2)
		.noStroke()
		.fill(#FF3300)
	;

	new HRotate(r1, 2);
}

void draw() {
	H.drawStage();
}


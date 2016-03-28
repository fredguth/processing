HRect r1, r2, r3;

void setup() {
	size(640,640);
	H.init(this).background(#202020).autoClear(false);
	smooth();

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc(width/2 - 160,height/2)
		.noStroke()
		.fill(#FF3300)
	;

	r2 = new HRect(100).rounding(10);
	H.add(r2)
		.loc(width/2,height/2)
		.noStroke()
		.fill(#0095a8)
	;

	r3 = new HRect(100).rounding(10);
	H.add(r3)
		.loc(width/2 + 160,height/2)
		.noStroke()
		.fill(#6666FF)
	;

	new HRotate(r1, 2);
	new HRotate(r2, 2);
	new HRotate(r3, 2);
}

void draw() {
	fill(#202020,10);
	rect(0,0,width,height);

	H.drawStage();
}

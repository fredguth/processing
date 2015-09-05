HRect d1,d2,d3,d4,d5;

void setup(){
	size(600,600);
	H.init(this).background(#ECECEC);

	d1 = new HRect();
	d1.size(50).rotation(45).anchorAt(H.CENTER).loc(100, height/2);
	HRotate r1 = new HRotate().target(d1).speed(1);
	H.add(d1);

	d2 = new HRect();
	d2.size(50).rotation(45).anchorAt(H.CENTER).loc(200, height/2);
	HRotate r2 = new HRotate().target(d2).speed(1.5);
	H.add(d2);

	d3 = new HRect();
	d3.size(50).rotation(45).anchorAt(H.CENTER).loc(300, height/2);
	HRotate r3 = new HRotate().target(d3).speed(2);
	H.add(d3);

	d4 = new HRect();
	d4.size(50).rotation(45).anchorAt(H.CENTER).loc(400, height/2);
	HRotate r4 = new HRotate().target(d4).speed(2.5);
	H.add(d4);

	d5 = new HRect();
	d5.size(50).rotation(45).anchorAt(H.CENTER).loc(500, height/2);
	HRotate r5 = new HRotate().target(d5).speed(3);
	H.add(d5);
}

void draw() {
	H.drawStage();

	line(0, height/2, width, height/2);

	ellipse(100, height/2, 5, 5);
	ellipse(200, height/2, 5, 5);
	ellipse(300, height/2, 5, 5);
	ellipse(400, height/2, 5, 5);
	ellipse(500, height/2, 5, 5);
}
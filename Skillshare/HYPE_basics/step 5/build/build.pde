HRect d1,d2,d3,d4,d5;

void setup(){
	size(600,600);
	H.init(this).background(#ECECEC).autoClear(true);

	d1 = new HRect();
	d1.size(50).rotation(45).loc(100, height/2);
	d1.fill( color(random(255),random(255),random(255)) );
	//HRotate r1 = new HRotate().target(d1).speed(1);
	H.add(d1);

	d2 = new HRect();
	d2.size(50).rotation(45).anchorAt(H.CENTER).loc(200, height/2);
	d2.fill( color(random(255),random(255),random(255)) );
	//HRotate r2 = new HRotate().target(d2).speed(1.5);
	H.add(d2);

// 	d3 = new HRect();
// 	d3.size(50).rotation(45).anchorAt(H.CENTER).loc(300, height/2);
// 	d3.fill( color(random(255),random(255),random(255)) );
// 	//HRotate r3 = new HRotate().target(d3).speed(2);
// 	H.add(d3);

// 	d4 = new HRect();
// 	d4.size(50).rotation(45).anchorAt(H.CENTER).loc(400, height/2);
// 	d4.fill( color(random(255),random(255),random(255)) );
// 	//HRotate r4 = new HRotate().target(d4).speed(2.5);
// 	H.add(d4);

// 	d5 = new HRect();
// 	d5.size(50).rotation(45).anchorAt(H.CENTER).loc(500, height/2);
// 	d5.fill( color(random(255),random(255),random(255)) );
// 	//HRotate r5 = new HRotate().target(d5).speed(3);
// 	H.add(d5);
// }

void draw() {
	d1.rotate(3);
	H.drawStage();

	line(0, height/2, width, height/2);


}
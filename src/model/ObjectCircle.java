package model;

public class ObjectCircle extends UGObject {

	public void setAABB() {
		this.aabb = new AABB();
		this.aabb.setxMin(this.gravityCentre.x - (this.size / 2));
		this.aabb.setxMax(this.gravityCentre.x + (this.size / 2));
		this.aabb.setyMin(this.gravityCentre.y - (this.size / 2));
		this.aabb.setyMax(this.gravityCentre.y + (this.size / 2));
	}
}

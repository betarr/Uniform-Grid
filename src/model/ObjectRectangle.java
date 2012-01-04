package model;

public class ObjectRectangle extends UGObject {
	public static final int LOCATION_HORIZONTAL = 0;
	public static final int LOCATION_VERTICAL = 1;

	private int location = ObjectRectangle.LOCATION_HORIZONTAL;

	public void setAABB() {
		this.aabb = new AABB();
		if (this.location == ObjectRectangle.LOCATION_HORIZONTAL) {
			this.aabb.setxMin(this.gravityCentre.x - this.size);
			this.aabb.setxMax(this.gravityCentre.x + this.size);
			this.aabb.setyMin(this.gravityCentre.y - (this.size / 2));
			this.aabb.setyMax(this.gravityCentre.y + (this.size / 2));
		} else if (this.location == ObjectRectangle.LOCATION_VERTICAL) {
			this.aabb.setxMin(this.gravityCentre.x - (this.size / 2));
			this.aabb.setxMax(this.gravityCentre.x + (this.size / 2));
			this.aabb.setyMin(this.gravityCentre.y - this.size);
			this.aabb.setyMax(this.gravityCentre.y + this.size);
		} else {
			this.aabb = null;
		}
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		if (location == ObjectRectangle.LOCATION_HORIZONTAL
				|| location == ObjectRectangle.LOCATION_VERTICAL) {
			this.location = location;
		} else {
			this.location = ObjectRectangle.LOCATION_HORIZONTAL;
		}
	}
}

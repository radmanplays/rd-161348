package com.mojang.rubydung.particle;

import com.mojang.rubydung.Entity;
import com.mojang.rubydung.level.Level;
import com.mojang.rubydung.level.Tesselator;

public class Particle extends Entity {
	private float xd;
	private float yd;
	private float zd;
	public int tex;
	private float uo;
	private float vo;

	public Particle(Level level, float x, float y, float z, float xa, float ya, float za, int tex) {
		super(level);
		this.tex = tex;
		this.setSize(0.2F, 0.2F);
		this.heightOffset = this.bbHeight / 2.0F;
		this.setPos(x, y, z);
		this.xd = xa + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
		this.yd = ya + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
		this.zd = za + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
		float speed = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
		float dd = (float)Math.sqrt((double)(this.xd * this.xd + this.yd * this.yd + this.zd * this.zd));
		this.xd = this.xd / dd * speed * 0.7F;
		this.yd = this.yd / dd * speed;
		this.zd = this.zd / dd * speed * 0.7F;
		this.uo = (float)Math.random() * 3.0F;
		this.vo = (float)Math.random() * 3.0F;
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if(Math.random() < 0.1D) {
			this.remove();
		}

		this.yd = (float)((double)this.yd - 0.06D);
		this.move(this.xd, this.yd, this.zd);
		this.xd *= 0.98F;
		this.yd *= 0.98F;
		this.zd *= 0.98F;
		if(this.onGround) {
			this.xd *= 0.7F;
			this.zd *= 0.7F;
		}

	}

	public void render(Tesselator t, float a, float xa, float ya, float za) {
		float u0 = ((float)(this.tex % 16) + this.uo / 4.0F) / 16.0F;
		float u1 = u0 + 0.999F / 64.0F;
		float v0 = ((float)(this.tex / 16) + this.vo / 4.0F) / 16.0F;
		float v1 = v0 + 0.999F / 64.0F;
		float r = 0.1F;
		float x = this.xo + (this.x - this.xo) * a;
		float y = this.yo + (this.y - this.yo) * a;
		float z = this.zo + (this.z - this.zo) * a;
		t.vertexUV(x - xa * r, y - ya * r, z - za * r, u0, v1);
		t.vertexUV(x - xa * r, y + ya * r, z - za * r, u0, v0);
		t.vertexUV(x + xa * r, y + ya * r, z + za * r, u1, v0);
		t.vertexUV(x + xa * r, y - ya * r, z + za * r, u1, v1);
	}
}

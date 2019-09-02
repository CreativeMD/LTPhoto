package com.ltphoto.render;

import com.ltphoto.items.TapeMessure;
import com.ltphoto.render.string.StringRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TapeRenderer {
	
	public static RenderWorldLastEvent event;
	
	@SubscribeEvent
	public void render(RenderWorldLastEvent event) {
		this.event = event;
		EntityPlayer player = Minecraft.getMinecraft().player;
		String item = "ltphoto:tapemessure";
		String mainItem = player.getHeldItemMainhand().getItem().getRegistryName().toString();
		TapeMessure tape = new TapeMessure();
		if (item.equals(mainItem)) {
			tape = (TapeMessure) player.getHeldItemMainhand().getItem();
		}
		
		if (tape.a != null && tape.b != null) {
			//player.sendStatusMessage(new TextComponentString(t), true);
			
			double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialTicks();
			double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialTicks();
			double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialTicks();
			
			double centerX_1 = tape.select.centerX;
			double centerY_1 = tape.select.centerY;
			double centerZ_1 = tape.select.centerZ;
			
			double centerX_2 = tape.select_2.centerX;
			double centerY_2 = tape.select_2.centerY;
			double centerZ_2 = tape.select_2.centerZ;
			
			StringRenderer.drawString(tape.select.boxCorner_1, event, 0.0F, 1.0F, 0F, 1.0F);
			
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.glLineWidth(2.0F);
			GlStateManager.disableTexture2D();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			
			/* 0 0 - x<x2 y>y2
			 * 1 -1 /
			 * 
			 * 0 0 - x>x2 y<y2
			 * -1 1 /
			 * 
			 * 0 0 - x>x2 y>y2
			 * -1 -1 /
			 * 
			 * 0 0 - x<x2 y<y2
			 * 1 1 / */
			
			if (centerX_1 < centerX_2 && centerY_1 > centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_6, tape.select_2.corner_2, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 < centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_3, tape.select_2.corner_7, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 > centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 < centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_4, tape.select_2.corner_8, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			if (centerX_1 < centerX_2 && centerY_1 > centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_7, tape.select_2.corner_3, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 < centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_2, tape.select_2.corner_6, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 > centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_8, tape.select_2.corner_4, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 < centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			if (centerX_1 == centerX_2 && centerY_1 == centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 > centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 < centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 == centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 == centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 == centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 == centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			if (centerX_1 == centerX_2 && centerY_1 > centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 < centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_4, tape.select_2.corner_8, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 > centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_8, tape.select_2.corner_4, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 == centerX_2 && centerY_1 < centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			if (centerX_1 > centerX_2 && centerY_1 > centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_5, tape.select_2.corner_1, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 < centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_2, tape.select_2.corner_6, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 > centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_7, tape.select_2.corner_3, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 < centerY_2 && centerZ_1 == centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			if (centerX_1 > centerX_2 && centerY_1 == centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_8, tape.select_2.corner_4, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 > centerX_2 && centerY_1 == centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_3, tape.select_2.corner_7, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 == centerY_2 && centerZ_1 > centerZ_2) {
				drawBoundingBox(tape.select.corner_6, tape.select_2.corner_2, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			} else if (centerX_1 < centerX_2 && centerY_1 == centerY_2 && centerZ_1 < centerZ_2) {
				drawBoundingBox(tape.select.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select.corner_1, tape.select.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
				drawBoundingBox(tape.select_2.corner_1, tape.select_2.corner_5, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			}
			
			//drawCircle(centerX_1, centerY_1, centerZ_1, centerX_2, centerY_2, centerZ_2, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
			GlStateManager.enableDepth();
			GlStateManager.depthMask(true);
			GlStateManager.enableTexture2D();
			GlStateManager.disableBlend();
		}
	}
	
	public static void drawBoundingBox(Vec3d vec_1, Vec3d vec_2, float red, float green, float blue, float alpha) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialTicks();
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialTicks();
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialTicks();
		
		double minX = vec_1.x;
		double minY = vec_1.y;
		double minZ = vec_1.z;
		
		double maxX = vec_2.x;
		double maxY = vec_2.y;
		double maxZ = vec_2.z;
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		
		drawBoundingBox(bufferbuilder, minX - d0 - 0.001, minY - d1 - 0.001, minZ - d2 - 0.001, maxX + 0.001 - d0, maxY - d1 + 0.001, maxZ - d2 + 0.001, red, green, blue, alpha);
		tessellator.draw();
	}
	
	public static void drawLine(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialTicks();
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialTicks();
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialTicks();
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(minX - d0 - 0.001, minY - d1 - 0.001, minZ - d2 - 0.001).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(maxX + 0.001 - d0, maxY - d1 + 0.001, maxZ - d2 + 0.001).color(red, green, blue, alpha).endVertex();
		tessellator.draw();
	}
	
	public static void drawCircle(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialTicks();
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialTicks();
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialTicks();
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		double pi = Math.PI;
		for (double y = 1; y < 10; y = y * pi) {
			bufferbuilder.pos(minX - d0 - 0.001, (y * minY) - d1 - 0.001, minZ - d2 - 0.001).color(red, green, blue, alpha).endVertex();
			
		}
		tessellator.draw();
	}
	
	public static void drawBoundingBox(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
		buffer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, 0.0F).endVertex();
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		buffer.pos(maxX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
	}
	
}
//drawLine(tape.select.corner_1.x, tape.select.corner_1.y, tape.select.corner_1.z, tape.select.corner_4.x, tape.select.corner_1.y, tape.select_2.corner_4.z, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
//drawLine(tape.select_2.corner_6.x, tape.select_2.corner_5.y, tape.select.corner_6.z, tape.select_2.corner_5.x, tape.select_2.corner_5.y, tape.select_2.corner_5.z, (float) 1.0, (float) 0.0, (float) 0.0, (float) 1.0);
package io.github.mysticalmike.gunmod.common.packets;

import java.io.IOException;
import java.util.UUID;

import io.github.mysticalmike.gunmod.common.entities.BulletEntity;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.ICustomPacket;

public class SpawnFastObjectPacket extends SCustomPayloadPlayPacket implements IPacket<IClientPlayNetHandler>, net.minecraftforge.fml.network.ICustomPacket<SCustomPayloadPlayPacket>
{
	private PacketBuffer data;
	
	public SpawnFastObjectPacket(ResourceLocation channelIn, PacketBuffer dataIn) {
		super(channelIn, dataIn);
		this.data = dataIn;
	}
	
	public static PacketBuffer writeVelocityPacketData(PacketBuffer buf, Vector3d vec3d) {
		buf.writeShort((int) vec3d.getX());
		buf.writeShort((int) vec3d.getX());
		buf.writeShort((int) vec3d.getX());
		return buf;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public PacketBuffer getBufferData() {
		return new PacketBuffer(this.data);
	}
}

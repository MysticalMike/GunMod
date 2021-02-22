package io.github.mysticalmike.gunmod.common.packets;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class PlayMessages extends FMLPlayMessages
{
	public static class SpawnEntity
    {
        private final Entity entity;
        private final int typeId;
        private final int entityId;
        private final UUID uuid;
        private final double posX, posY, posZ;
        private final byte pitch, yaw, headYaw;
        private final int velX, velY, velZ;
        private final PacketBuffer buf;
        
        public SpawnEntity(Entity e)
        {
            this.entity = e;
            this.typeId = Registry.ENTITY_TYPE.getId(e.getType()); //TODO: Codecs
            this.entityId = e.getEntityId();
            this.uuid = e.getUniqueID();
            this.posX = e.getPosX();
            this.posY = e.getPosY();
            this.posZ = e.getPosZ();
            this.pitch = (byte) MathHelper.floor(e.rotationPitch * 256.0F / 360.0F);
            this.yaw = (byte) MathHelper.floor(e.rotationYaw * 256.0F / 360.0F);
            this.headYaw = (byte) (e.getRotationYawHead() * 256.0F / 360.0F);
            Vector3d vec3d = e.getMotion();
            double d1 = MathHelper.clamp(vec3d.x, -3.9D, 3.9D);
            double d2 = MathHelper.clamp(vec3d.y, -3.9D, 3.9D);
            double d3 = MathHelper.clamp(vec3d.z, -3.9D, 3.9D);
            this.velX = (int)(d1 * 8000.0D);
            this.velY = (int)(d2 * 8000.0D);
            this.velZ = (int)(d3 * 8000.0D);
            this.buf = null;
        }
    }
}

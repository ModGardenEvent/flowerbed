package net.modgarden.flowerbed.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.Unit;
import net.modgarden.flowerbed.Flowerbed;

@SuppressWarnings("UnstableApiUsage")
public class FlowerbedAttachments {
	public static final AttachmentType<Unit> ACCEPT_PVP = AttachmentRegistry.create(
			Flowerbed.asResource("accept_pvp"), unitBuilder ->
					unitBuilder
							.copyOnDeath()
							.persistent(Unit.CODEC)
							.syncWith(Unit.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	public static void init() {

	}
}

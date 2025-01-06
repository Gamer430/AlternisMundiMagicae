package org.magicmaker.alternismundimagicae.cca.entitycca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataImpl implements PlayerData, AutoSyncedComponent {
    private int markValue;
    private boolean isTeleportable;
    private boolean isBlacklisted;
    private HashMap<UUID, Integer> MarkingsMap = new HashMap<>();

    @Override
    public int getMarkValue() {
        return markValue;
    }

    @Override
    public void setMarkValue(int amount) {
        this.markValue = amount;
    }

    @Override
    public boolean getTeleportable() {
        return isTeleportable;
    }

    @Override
    public void setTeleportable(boolean isTeleportable) {
        this.isTeleportable = isTeleportable;
    }

    @Override
    public boolean getBlacklisted() {
        return isBlacklisted;
    }

    @Override
    public void setBlacklisted(boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }

    @Override
    public void updateMarkedMap(UUID uuid, int value) {
        MarkingsMap.put(uuid, value);
        updateBooleans(); // Update the booleans based on the new markings.
    }

    private void updateBooleans() {
        // Example logic: If any marking has a value > 10, the player is blacklisted.
        isBlacklisted = MarkingsMap.values().stream().anyMatch(mark -> mark > 10);

        // Example logic: If total mark value exceeds a threshold, the player is teleportable.
        int totalMarkValue = MarkingsMap.values().stream().mapToInt(Integer::intValue).sum();
        isTeleportable = totalMarkValue >= 50;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.markValue = nbtCompound.getInt("markValue");
        this.isTeleportable = nbtCompound.getBoolean("isTeleportable");
        this.isBlacklisted = nbtCompound.getBoolean("isBlacklisted");

        MarkingsMap.clear();
        NbtList markingsList = nbtCompound.getList("MarkingsMap", NbtElement.COMPOUND_TYPE);
        for (NbtElement element : markingsList) {
            NbtCompound markEntry = (NbtCompound) element;
            UUID uuid = markEntry.getUuid("uuid");
            int value = markEntry.getInt("value");
            MarkingsMap.put(uuid, value);
        }
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("markValue", markValue);
        nbtCompound.putBoolean("isTeleportable", isTeleportable);
        nbtCompound.putBoolean("isBlacklisted", isBlacklisted);

        NbtList markingsList = new NbtList();
        for (Map.Entry<UUID, Integer> entry : MarkingsMap.entrySet()) {
            NbtCompound markEntry = new NbtCompound();
            markEntry.putUuid("uuid", entry.getKey());
            markEntry.putInt("value", entry.getValue());
            markingsList.add(markEntry);
        }
        nbtCompound.put("MarkingsMap", markingsList);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return AutoSyncedComponent.super.shouldSyncWith(player);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeInt(markValue);
        buf.writeBoolean(isTeleportable);
        buf.writeBoolean(isBlacklisted);

        buf.writeInt(MarkingsMap.size());
        for (Map.Entry<UUID, Integer> entry : MarkingsMap.entrySet()) {
            buf.writeUuid(entry.getKey());
            buf.writeInt(entry.getValue());
        }
        AutoSyncedComponent.super.writeSyncPacket(buf, recipient);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        markValue = buf.readInt();
        isTeleportable = buf.readBoolean();
        isBlacklisted = buf.readBoolean();

        MarkingsMap.clear();
        int mapSize = buf.readInt();
        for (int i = 0; i < mapSize; i++) {
            UUID uuid = buf.readUuid();
            int value = buf.readInt();
            MarkingsMap.put(uuid, value);
        }
        AutoSyncedComponent.super.applySyncPacket(buf);
    }
}

package uk.co.shadeddimensions.enhancedportals.tileentity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import uk.co.shadeddimensions.enhancedportals.network.packet.MainPacket;
import uk.co.shadeddimensions.enhancedportals.network.packet.PacketRequestData;
import uk.co.shadeddimensions.enhancedportals.tileentity.frame.TilePortalController;
import uk.co.shadeddimensions.enhancedportals.util.ChunkCoordinateUtils;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TilePortalFrame extends TileEP implements IInventory
{
    public ChunkCoordinates controller;

    public TilePortalFrame()
    {
        
    }

    public TilePortalController getController()
    {
        if (controller != null)
        {
            TileEntity tile = worldObj.getBlockTileEntity(controller.posX, controller.posY, controller.posZ);
            
            if (tile != null && tile instanceof TilePortalController)
            {
                return (TilePortalController) tile;
            }
        }
        
        return null;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);

        ChunkCoordinateUtils.saveChunkCoord(tagCompound, controller, "controller");
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);

        controller = ChunkCoordinateUtils.loadChunkCoord(tagCompound, "controller");
    }

    @Override
    public void validate()
    {
        super.validate();

        if (worldObj.isRemote)
        {
            PacketDispatcher.sendPacketToServer(MainPacket.makePacket(new PacketRequestData(this)));
        }
    }

    public boolean activate(EntityPlayer player)
    {
        return false;
    }

    public void neighborChanged(int id)
    {

    }

    public void selfBroken()
    {
        TilePortalController control = getController();

        if (control != null)
        {
            control.selfBroken();
        }
    }

    public int isProvidingStrongPower(int side)
    {
        return 0;
    }

    public int isProvidingWeakPower(int side)
    {
        return 0;
    }

    public void scheduledTick(Random random)
    {

    }

    public void entityTouch(Entity entity)
    {

    }

    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
    }

    @Override
    public String getInvName()
    {
        return null;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void openChest()
    {
    }

    @Override
    public void closeChest()
    {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return false;
    }
}

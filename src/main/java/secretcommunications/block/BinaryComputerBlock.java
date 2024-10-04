
package secretcommunications.block;

import secretcommunications.world.inventory.BinarycomputertextuiMenu;

import secretcommunications.procedures.CheckifbutfotblockandstufProcedure;

import secretcommunications.block.entity.BinaryComputerBlockEntity;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

public class BinaryComputerBlock extends Block implements EntityBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public BinaryComputerBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1f, 10f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				if (s.getValue(BLOCKSTATE) == 2)
					return 0;
				if (s.getValue(BLOCKSTATE) == 3)
					return 0;
				return 0;
			}
		}.getLightLevel())).noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 7, 16, 2, 16), box(1, 0.43996, 2.67881, 15, 4.43996, 7.67881));
				case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 16, 2, 9), box(1, 0.43996, 8.32119, 15, 4.43996, 13.32119));
				case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(7, 1, 0, 16, 2, 16), box(2.67881, 0.43996, 1, 7.67881, 4.43996, 15));
				case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 9, 2, 16), box(8.32119, 0.43996, 1, 13.32119, 4.43996, 15));
			};
		}
		if (state.getValue(BLOCKSTATE) == 2) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 7, 16, 2, 16), box(1, 0.43996, 2.67881, 15, 4.43996, 7.67881));
				case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 16, 2, 9), box(1, 0.43996, 8.32119, 15, 4.43996, 13.32119));
				case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(7, 1, 0, 16, 2, 16), box(2.67881, 0.43996, 1, 7.67881, 4.43996, 15));
				case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 9, 2, 16), box(8.32119, 0.43996, 1, 13.32119, 4.43996, 15));
			};
		}
		if (state.getValue(BLOCKSTATE) == 3) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 7, 16, 2, 16), box(1, 0.43996, 2.67881, 15, 4.43996, 7.67881));
				case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 16, 2, 9), box(1, 0.43996, 8.32119, 15, 4.43996, 13.32119));
				case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(7, 1, 0, 16, 2, 16), box(2.67881, 0.43996, 1, 7.67881, 4.43996, 15));
				case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 9, 2, 16), box(8.32119, 0.43996, 1, 13.32119, 4.43996, 15));
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 7, 16, 2, 16), box(1, 0.43996, 2.67881, 15, 4.43996, 7.67881));
			case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 16, 2, 9), box(1, 0.43996, 8.32119, 15, 4.43996, 13.32119));
			case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(7, 1, 0, 16, 2, 16), box(2.67881, 0.43996, 1, 7.67881, 4.43996, 15));
			case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(0, 1, 0, 9, 2, 16), box(8.32119, 0.43996, 1, 13.32119, 4.43996, 15));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		CheckifbutfotblockandstufProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		if (entity instanceof ServerPlayer player) {
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Binary Computer");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new BinarycomputertextuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BinaryComputerBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof BinaryComputerBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof BinaryComputerBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}

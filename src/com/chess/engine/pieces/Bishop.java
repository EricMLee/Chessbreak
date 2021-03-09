package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9};
    private final int xPosition;
    private final int yPosition;
    public Bishop(Alliance pieceAlliance, int piecePosition){
        super(PieceType.BISHOP, pieceAlliance, piecePosition, true);
        xPosition = piecePosition%8;
        yPosition = piecePosition/8;
    }

    public Collection<Move> calculateLegalMoves(final Board board){
        final List<Move> legalMoves = new ArrayList<>();
        int x = xPosition;
        int y = yPosition;
        do {
            x--;
            y--;
            if(x == -1 || y == -1){
                break;
            } else {
                if(BoardUtils.isValidTileCoordinate(y*8+x)){
                    final Tile candidateDestinationTile = board.getTile(y*8+x);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, y*8+x));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.MajorAttackMove(board, this, y*8+x, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        } while (true);
        x = xPosition;
        y = yPosition;
        do {
            x--;
            y++;
            if(x == -1 || y == 8){
                break;
            } else {
                if(BoardUtils.isValidTileCoordinate(y*8+x)){
                    final Tile candidateDestinationTile = board.getTile(y*8+x);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, y*8+x));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.AttackMove(board, this, y*8+x, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        } while (true);
        x = xPosition;
        y = yPosition;
        do {
            x++;
            y--;
            if(x == 8 || y == -1){
                break;
            } else {
                if(BoardUtils.isValidTileCoordinate(y*8+x)){
                    final Tile candidateDestinationTile = board.getTile(y*8+x);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, y*8+x));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.AttackMove(board, this, y*8+x, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        } while (true);
        x = xPosition;
        y = yPosition;
        do {
            x++;
            y++;
            if(x == 8 || y == 8){
                break;
            } else {
                if(BoardUtils.isValidTileCoordinate(y*8+x)){
                    final Tile candidateDestinationTile = board.getTile(y*8+x);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, y*8+x));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.AttackMove(board, this, y*8+x, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        } while (true);
        return ImmutableList.copyOf(legalMoves);
    }

//    @Override
//    public Collection<Move> calculateLegalMoves(final Board board) {
//        final List<Move> legalMoves = new ArrayList<>();
//        for(final int candidateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
//            int candidateDestinationCoordinate = this.piecePosition;
//            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
//                if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateOffset) || isEighthColumnExclusion(candidateDestinationCoordinate, candidateOffset)){
//                    break;
//                }
//                candidateDestinationCoordinate += candidateOffset;
//                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
//                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
//                    if(!candidateDestinationTile.isTileOccupied()){
//                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
//                    }else{
//                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
//                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
//                        if(this.pieceAlliance != pieceAlliance){
//                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//        return ImmutableList.copyOf(legalMoves);
//    }

    @Override
    public Bishop movePiece(Move move) {
        return new Bishop(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == 9 || candidateOffset == -7);
    }
    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }

}

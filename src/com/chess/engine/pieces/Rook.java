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

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -8, -1, 1, 8};
    private final int xPosition;
    private final int yPosition;
    public Rook(Alliance pieceAlliance, int piecePosition){
        super(PieceType.ROOK, pieceAlliance, piecePosition, true);
        xPosition = piecePosition%8;
        yPosition = piecePosition/8;
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int x = xPosition;
        int y = yPosition;
        do {
            y--;
            if(y == -1){
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
            x--;
            if(x == -1){
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
            if(x == 8){
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
            y++;
            if(y == 8){
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
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == 1);
    }
    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }
}

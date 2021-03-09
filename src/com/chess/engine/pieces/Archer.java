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

import static com.chess.engine.board.Move.*;

public class Archer extends Piece {

    private final int xPosition;
    private final int yPosition;

    public Archer(Alliance pieceAlliance, int piecePosition){
        super(PieceType.ARCHER, pieceAlliance, piecePosition, true);
        yPosition = piecePosition/8;
        xPosition = piecePosition%8;
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
                            legalMoves.add(new Move.AttackMove(board, this, this.piecePosition, pieceAtDestination));
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
                            legalMoves.add(new Move.AttackMove(board, this, y*8+x, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        } while (true);
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public Archer movePiece(Move move) {
        return new Archer(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ( candidateOffset == 10 || candidateOffset == -6 );
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ( candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
    }

    @Override
    public String toString(){
        return PieceType.ARCHER.toString();
    }
}

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

public class Spartan extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-1, 1, 8, -8, -6, 6, -10, 10, -17, -15, 17, 15};
    private final int xPosition;
    private final int yPosition;
    public Spartan(Alliance pieceAlliance, int piecePosition){
        super(PieceType.SPARTAN, pieceAlliance, piecePosition, true);
        xPosition = piecePosition%8;
        yPosition = piecePosition/8;
    }

    public Collection<Move> calculateLegalMoves(final Board board){
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidate;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstColumnExclusion(this.piecePosition, currentCandidate) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidate) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidate) ||
                        isEighthColumnExclusion(this.piecePosition, currentCandidate)){
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move.MajorAttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


    @Override
    public Spartan movePiece(Move move) {
        return new Spartan(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -10 || candidateOffset == -17 || candidateOffset == 6 || candidateOffset == 15 );
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == 10 || candidateOffset == -6);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1 || candidateOffset == 10 || candidateOffset == 17 || candidateOffset == -6 || candidateOffset == -15 );
    }
    @Override
    public String toString(){
        return "S";
    }

}

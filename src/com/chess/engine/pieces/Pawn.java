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

public class Pawn extends Piece {

    private final static  int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};
    private int rowPos;
    private int colPos;
    //public Pawn(Alliance pieceAlliance, int piecePosition){
    //    super(PieceType.PAWN, pieceAlliance, piecePosition);
    //}

    public Pawn(final Alliance alliance,
                final int piecePosition,
                final boolean isFirstMove) {
        super(PieceType.PAWN, alliance, piecePosition, isFirstMove);
        this.rowPos = piecePosition / 8;
        this.colPos = piecePosition % 8;
    }

    public Pawn(final Alliance allegiance,
                final int piecePosition) {
        super(PieceType.PAWN, allegiance, piecePosition, true);
        this.rowPos = piecePosition / 8;
        this.colPos = piecePosition % 8;
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        if(!(board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + colPos).isTileOccupied())){
            legalMoves.add(new Move.MajorMove(board, this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + colPos));
        }
        if(isFirstMove() && !(board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + colPos).isTileOccupied()) &&
                !(board.getTile((rowPos+(2*this.pieceAlliance.getDirection())) * 8 + colPos).isTileOccupied())){
            legalMoves.add(new Move.MajorMove(board, this, (rowPos+(2*this.pieceAlliance.getDirection())) * 8 + colPos));
        }
        if(colPos + 1 != 8 && colPos - 1 != -1){
            Tile lookingTile = board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos + 1));
            if(lookingTile.isTileOccupied() && lookingTile.getPiece().getPieceAlliance() != this.pieceAlliance){
                legalMoves.add(new Move.AttackMove(board, this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos + 1) , lookingTile.getPiece()));
            }
            if(!lookingTile.isTileOccupied() && board.getEnPassantPawn() != null){
                System.out.println("can enPassant");
                if(board.getEnPassantPawn().getPiecePosition() == (this.piecePosition + 1)){
                    System.out.println("can enpassant");
                    final Piece pieceOnCandidate = board.getEnPassantPawn();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        legalMoves.add(new Move.PawnEnPassantMove(board,this, this.piecePosition + 9, pieceOnCandidate));
                    }
                }
            }
            lookingTile = board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos - 1));
            if(lookingTile.isTileOccupied() && lookingTile.getPiece().getPieceAlliance() != this.pieceAlliance){
                legalMoves.add(new Move.AttackMove(board, this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos - 1) , lookingTile.getPiece()));
            }
            if(!lookingTile.isTileOccupied() && board.getEnPassantPawn() != null){
                if(board.getEnPassantPawn().getPiecePosition() == (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))){
                    final Piece pieceOnCandidate = board.getEnPassantPawn();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        legalMoves.add(new Move.PawnEnPassantMove(board,this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos - 1) ,pieceOnCandidate));
                    }
                }
            }
        }
        if(colPos + 1 == 8){
            Tile lookingTile = board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos - 1));
            if(lookingTile.isTileOccupied() && lookingTile.getPiece().getPieceAlliance() != this.pieceAlliance){
                legalMoves.add(new Move.AttackMove(board, this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos - 1) , lookingTile.getPiece()));
            }
        }
        if(colPos - 1 == -1){
            Tile lookingTile = board.getTile((rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos + 1));
            if(lookingTile.isTileOccupied() && lookingTile.getPiece().getPieceAlliance() != this.pieceAlliance){
                legalMoves.add(new Move.AttackMove(board, this, (rowPos+(1*this.pieceAlliance.getDirection())) * 8 + (colPos + 1) , lookingTile.getPiece()));
            }
        }
//        if(isFirstMove() && )(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece)
//        for(final int currentCandidateoffset : CANDIDATE_MOVE_COORDINATE){
//            int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateoffset);
//
//            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
//                continue;
//            }
//            if(currentCandidateoffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
//                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
//
//            }else if(this.isFirstMove()  ){
//                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
//                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
//                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
//                }
//            } else if(currentCandidateoffset == 7 &&
//                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ) ||
//                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))){
//                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
//                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
//                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
//                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
//                    }
//                }
//            } else if(currentCandidateoffset == 9 &&
//                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ) ||
//                            (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))){
//                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
//                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
//                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
//                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
//                    }
//                }
//            }
//        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate(), false);
    }
    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}

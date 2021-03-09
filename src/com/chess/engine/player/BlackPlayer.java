package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection <Move> blackStandardLegalMoves){

        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance(){
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }


    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {

//        if(!hasCastleOpportunities()) {
//            return Collections.emptyList();
//        }

        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            //whites king side castle
            if(this.board.getTile(5).isTileOccupied() && this.board.getTile(6).isTileOccupied() ) {
                final Tile kingSideRook = this.board.getTile(7);
                if(kingSideRook.isTileOccupied() && kingSideRook.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(6, opponentLegals).isEmpty()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 6, (Rook) kingSideRook.getPiece(), kingSideRook.getTileCoordinate(), 5));

//                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
//                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
//                        }
                    }
                }
            }
            //whites queen side castle
            if(this.board.getTile(1).isTileOccupied()  && this.board.getTile(2).isTileOccupied()  &&
                    this.board.getTile(3).isTileOccupied())  {
                final Tile queenSideRook = this.board.getTile(0);
                if(queenSideRook.isTileOccupied() && queenSideRook.getPiece().isFirstMove()) {

                    if(Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(3, opponentLegals).isEmpty()) {
                        kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 2, (Rook) queenSideRook.getPiece(), queenSideRook.getTileCoordinate(), 3));

//                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
//                            kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) queenSideRook, queenSideRook.getPiecePosition(), 59));
//                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }

}

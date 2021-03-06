package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WhitePlayer extends Player{
    public WhitePlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection <Move> blackStandardLegalMoves){
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance(){
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }


    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {

//        if(!hasCastleOpportunities()) {
//            return Collections.emptyList();
//        }

        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 60 && !this.isInCheck()) {
            //whites king side castle
            if(this.board.getTile(61).isTileOccupied() && this.board.getTile(62).isTileOccupied() ) {
                final Tile kingSideRook = this.board.getTile(63);
                if(kingSideRook.isTileOccupied() && kingSideRook.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentLegals).isEmpty()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook.getPiece(), kingSideRook.getTileCoordinate(), 61));
//                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
//                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
//                        }
                    }
                }
            }
            //whites queen side castle
            if(this.board.getTile(59).isTileOccupied()  && this.board.getTile(58).isTileOccupied()  &&
                    this.board.getTile(57).isTileOccupied())  {
                final Tile queenSideRook = this.board.getTile(56);
                if(queenSideRook.isTileOccupied() && queenSideRook.getPiece().isFirstMove()) {

                    if(Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(59, opponentLegals).isEmpty()) {
                        kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) queenSideRook.getPiece(), queenSideRook.getTileCoordinate(), 59));
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

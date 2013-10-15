// ----------------------------------------------------------------------------
// Copyright (C) 2013 Louise A. Dennis, and  Michael Fisher 
//
// This file is part of the Lego Rover Library.
// 
// The Lego Rover Library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// The Lego Rover Library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with the EASS Library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------
// $ANTLR 3.4 /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g 2012-11-29 11:37:30

package eass.parser;

import ail.syntax.ast.*;
import eass.syntax.ast.*;
import java.util.HashMap;


import mcaplantlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class EASSParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSTRACTION", "ACHIEVE", "ACHIEVEGOAL", "BELIEFRULES", "BELIEFS", "BELIEVE", "BRULEARROW", "CALCULATE", "CLOSE", "COLON", "COMMA", "COMMENT", "CONST", "CURLYCLOSE", "CURLYOPEN", "DIV", "DOUBLEQUOTE", "EASS", "EQ", "EQ_ASSGN", "GOAL", "GOALS", "LESS", "LINE_COMMENT", "LOCK", "MINUS", "MOD", "MULT", "NAME", "NEWLINE", "NOT", "NUMBER", "OPEN", "PERFORM", "PERFORMGOAL", "PLANS", "PLUS", "POINT", "QUERY", "QUERYCOM", "RECEIVED", "RULEARROW", "SEMI", "SEND", "SENT", "SHRIEK", "SQCLOSE", "SQOPEN", "STRING", "TELL", "TRUE", "UPDATE", "VAR", "WAIT", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSTRACTION=4;
    public static final int ACHIEVE=5;
    public static final int ACHIEVEGOAL=6;
    public static final int BELIEFRULES=7;
    public static final int BELIEFS=8;
    public static final int BELIEVE=9;
    public static final int BRULEARROW=10;
    public static final int CALCULATE=11;
    public static final int CLOSE=12;
    public static final int COLON=13;
    public static final int COMMA=14;
    public static final int COMMENT=15;
    public static final int CONST=16;
    public static final int CURLYCLOSE=17;
    public static final int CURLYOPEN=18;
    public static final int DIV=19;
    public static final int DOUBLEQUOTE=20;
    public static final int EASS=21;
    public static final int EQ=22;
    public static final int EQ_ASSGN=23;
    public static final int GOAL=24;
    public static final int GOALS=25;
    public static final int LESS=26;
    public static final int LINE_COMMENT=27;
    public static final int LOCK=28;
    public static final int MINUS=29;
    public static final int MOD=30;
    public static final int MULT=31;
    public static final int NAME=32;
    public static final int NEWLINE=33;
    public static final int NOT=34;
    public static final int NUMBER=35;
    public static final int OPEN=36;
    public static final int PERFORM=37;
    public static final int PERFORMGOAL=38;
    public static final int PLANS=39;
    public static final int PLUS=40;
    public static final int POINT=41;
    public static final int QUERY=42;
    public static final int QUERYCOM=43;
    public static final int RECEIVED=44;
    public static final int RULEARROW=45;
    public static final int SEMI=46;
    public static final int SEND=47;
    public static final int SENT=48;
    public static final int SHRIEK=49;
    public static final int SQCLOSE=50;
    public static final int SQOPEN=51;
    public static final int STRING=52;
    public static final int TELL=53;
    public static final int TRUE=54;
    public static final int UPDATE=55;
    public static final int VAR=56;
    public static final int WAIT=57;
    public static final int WS=58;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public EASSParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public EASSParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return EASSParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g"; }


    	private static HashMap<String,Abstract_VarTerm> variables = new HashMap<String,Abstract_VarTerm>();
    	private Abstract_Literal agentname = new Abstract_Literal("");
    	


    // $ANTLR start "mas"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:59:1: mas returns [Abstract_MAS mas] :glist= eassagents ;
    public final Abstract_MAS mas() throws RecognitionException {
        Abstract_MAS mas = null;


        ArrayList<Abstract_EASSAgent> glist =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:59:32: (glist= eassagents )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:59:34: glist= eassagents
            {
            mas = new Abstract_MAS();

            pushFollow(FOLLOW_eassagents_in_mas89);
            glist=eassagents();

            state._fsp--;


            mas.setAgs(glist);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return mas;
    }
    // $ANTLR end "mas"



    // $ANTLR start "eassagents"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:62:1: eassagents returns [ArrayList<Abstract_EASSAgent> gags] : EASS (g= eassagent )+ ;
    public final ArrayList<Abstract_EASSAgent> eassagents() throws RecognitionException {
        ArrayList<Abstract_EASSAgent> gags = null;


        Abstract_EASSAgent g =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:62:55: ( EASS (g= eassagent )+ )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:62:57: EASS (g= eassagent )+
            {
            match(input,EASS,FOLLOW_EASS_in_eassagents102); 

            gags=new ArrayList<Abstract_EASSAgent>();

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:63:2: (g= eassagent )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ABSTRACTION||LA1_0==NAME) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:63:3: g= eassagent
            	    {
            	    pushFollow(FOLLOW_eassagent_in_eassagents111);
            	    g=eassagent();

            	    state._fsp--;


            	    gags.add(g);

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return gags;
    }
    // $ANTLR end "eassagents"



    // $ANTLR start "eassagent"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:65:1: eassagent returns [Abstract_EASSAgent g] : ( NAME w= word | ABSTRACTION w= word ) BELIEFS (l= literal )* ( BELIEFRULES (r= brule )* )? GOALS (gl= goal )* PLANS (p= plan )* ;
    public final Abstract_EASSAgent eassagent() throws RecognitionException {
        Abstract_EASSAgent g = null;


        String w =null;

        Abstract_Literal l =null;

        Abstract_Rule r =null;

        Abstract_Goal gl =null;

        Abstract_GPlan p =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:65:42: ( ( NAME w= word | ABSTRACTION w= word ) BELIEFS (l= literal )* ( BELIEFRULES (r= brule )* )? GOALS (gl= goal )* PLANS (p= plan )* )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:66:2: ( NAME w= word | ABSTRACTION w= word ) BELIEFS (l= literal )* ( BELIEFRULES (r= brule )* )? GOALS (gl= goal )* PLANS (p= plan )*
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:66:2: ( NAME w= word | ABSTRACTION w= word )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==NAME) ) {
                alt2=1;
            }
            else if ( (LA2_0==ABSTRACTION) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:66:3: NAME w= word
                    {
                    match(input,NAME,FOLLOW_NAME_in_eassagent130); 

                    pushFollow(FOLLOW_word_in_eassagent134);
                    w=word();

                    state._fsp--;


                    try {g = new Abstract_EASSAgent(w);} catch (Exception e) {System.err.println(e);}
                    	              agentname = new Abstract_Literal(w);

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:68:2: ABSTRACTION w= word
                    {
                    match(input,ABSTRACTION,FOLLOW_ABSTRACTION_in_eassagent141); 

                    pushFollow(FOLLOW_word_in_eassagent145);
                    w=word();

                    state._fsp--;


                    String s = "abstraction_" + w; g = new Abstract_EASSAgent(s); g.setAbstraction(w);

                    }
                    break;

            }


            match(input,BELIEFS,FOLLOW_BELIEFS_in_eassagent157); 

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:70:10: (l= literal )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==CONST||LA3_0==NOT||LA3_0==TRUE||LA3_0==VAR) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:70:11: l= literal
            	    {
            	    pushFollow(FOLLOW_literal_in_eassagent162);
            	    l=literal();

            	    state._fsp--;


            	    g.addInitialBel(l);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:71:2: ( BELIEFRULES (r= brule )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==BELIEFRULES) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:71:3: BELIEFRULES (r= brule )*
                    {
                    match(input,BELIEFRULES,FOLLOW_BELIEFRULES_in_eassagent170); 

                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:71:15: (r= brule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==BELIEVE||LA4_0==GOAL||LA4_0==MINUS||(LA4_0 >= NUMBER && LA4_0 <= OPEN)||LA4_0==SENT||LA4_0==TRUE||LA4_0==VAR) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:71:16: r= brule
                    	    {
                    	    pushFollow(FOLLOW_brule_in_eassagent175);
                    	    r=brule();

                    	    state._fsp--;


                    	    g.addRule(r);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,GOALS,FOLLOW_GOALS_in_eassagent184); 

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:72:8: (gl= goal )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==CONST||LA6_0==NOT||LA6_0==TRUE||LA6_0==VAR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:72:9: gl= goal
            	    {
            	    pushFollow(FOLLOW_goal_in_eassagent189);
            	    gl=goal();

            	    state._fsp--;


            	    g.addInitialGoal(gl);

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            match(input,PLANS,FOLLOW_PLANS_in_eassagent196); 

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:73:8: (p= plan )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==MINUS||LA7_0==PLUS) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:73:9: p= plan
            	    {
            	    pushFollow(FOLLOW_plan_in_eassagent201);
            	    p=plan();

            	    state._fsp--;


            	    try {g.addPlan(p);} catch (Exception e) {System.err.println(e);}

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return g;
    }
    // $ANTLR end "eassagent"



    // $ANTLR start "goal"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:76:1: goal returns [Abstract_Goal g] : l= literal SQOPEN ( ACHIEVEGOAL | PERFORMGOAL ) SQCLOSE ;
    public final Abstract_Goal goal() throws RecognitionException {
        Abstract_Goal g = null;


        Abstract_Literal l =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:76:32: (l= literal SQOPEN ( ACHIEVEGOAL | PERFORMGOAL ) SQCLOSE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:76:34: l= literal SQOPEN ( ACHIEVEGOAL | PERFORMGOAL ) SQCLOSE
            {
            pushFollow(FOLLOW_literal_in_goal221);
            l=literal();

            state._fsp--;


            match(input,SQOPEN,FOLLOW_SQOPEN_in_goal223); 

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:76:51: ( ACHIEVEGOAL | PERFORMGOAL )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ACHIEVEGOAL) ) {
                alt8=1;
            }
            else if ( (LA8_0==PERFORMGOAL) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:76:52: ACHIEVEGOAL
                    {
                    match(input,ACHIEVEGOAL,FOLLOW_ACHIEVEGOAL_in_goal226); 

                    g = new Abstract_Goal(l, Abstract_Goal.achieveGoal);

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:77:2: PERFORMGOAL
                    {
                    match(input,PERFORMGOAL,FOLLOW_PERFORMGOAL_in_goal234); 

                    g = new Abstract_Goal(l, Abstract_Goal.performGoal);

                    }
                    break;

            }


            match(input,SQCLOSE,FOLLOW_SQCLOSE_in_goal239); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return g;
    }
    // $ANTLR end "goal"



    // $ANTLR start "plan"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:80:1: plan returns [Abstract_GPlan p] : e= event COLON CURLYOPEN ( NOT )? gb= guard_atom ( COMMA ( NOT )? gb= guard_atom )* CURLYCLOSE ( RULEARROW d= deed[deeds] ( ',' d= deed[deeds] )* )? SEMI ;
    public final Abstract_GPlan plan() throws RecognitionException {
        Abstract_GPlan p = null;


        Abstract_Event e =null;

        Abstract_GuardAtom gb =null;

        Abstract_Deed d =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:81:2: (e= event COLON CURLYOPEN ( NOT )? gb= guard_atom ( COMMA ( NOT )? gb= guard_atom )* CURLYCLOSE ( RULEARROW d= deed[deeds] ( ',' d= deed[deeds] )* )? SEMI )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:81:4: e= event COLON CURLYOPEN ( NOT )? gb= guard_atom ( COMMA ( NOT )? gb= guard_atom )* CURLYCLOSE ( RULEARROW d= deed[deeds] ( ',' d= deed[deeds] )* )? SEMI
            {
            pushFollow(FOLLOW_event_in_plan255);
            e=event();

            state._fsp--;


            ArrayList<Abstract_Deed> deeds=new ArrayList<Abstract_Deed>(); Abstract_Guard g = new Abstract_Guard();

            match(input,COLON,FOLLOW_COLON_in_plan263); 

            match(input,CURLYOPEN,FOLLOW_CURLYOPEN_in_plan265); 

            boolean gneg=true;

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:82:40: ( NOT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NOT) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:82:41: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_plan270); 

                    gneg=false;

                    }
                    break;

            }


            pushFollow(FOLLOW_guard_atom_in_plan278);
            gb=guard_atom();

            state._fsp--;


            g.add(gb, gneg);

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:3: ( COMMA ( NOT )? gb= guard_atom )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==COMMA) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:4: COMMA ( NOT )? gb= guard_atom
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_plan286); 

            	    gneg=true;

            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:23: ( NOT )?
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==NOT) ) {
            	        alt10=1;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:24: NOT
            	            {
            	            match(input,NOT,FOLLOW_NOT_in_plan291); 

            	            gneg=false;

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_guard_atom_in_plan299);
            	    gb=guard_atom();

            	    state._fsp--;


            	    g.add(gb, gneg);

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            match(input,CURLYCLOSE,FOLLOW_CURLYCLOSE_in_plan305); 

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:93: ( RULEARROW d= deed[deeds] ( ',' d= deed[deeds] )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULEARROW) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:83:94: RULEARROW d= deed[deeds] ( ',' d= deed[deeds] )*
                    {
                    match(input,RULEARROW,FOLLOW_RULEARROW_in_plan308); 

                    pushFollow(FOLLOW_deed_in_plan313);
                    d=deed(deeds);

                    state._fsp--;


                    deeds.add(d);

                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:84:35: ( ',' d= deed[deeds] )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMA) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:84:36: ',' d= deed[deeds]
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_plan319); 

                    	    pushFollow(FOLLOW_deed_in_plan323);
                    	    d=deed(deeds);

                    	    state._fsp--;


                    	    deeds.add(d);

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,SEMI,FOLLOW_SEMI_in_plan334); 

            p = new Abstract_GPlan(e, g, deeds); p.reverseBody(); variables.clear();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "plan"



    // $ANTLR start "event"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:1: event returns [Abstract_Event e] : ( PLUS ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) ) | MINUS (l= literal | SHRIEK g= goal ) ) ;
    public final Abstract_Event event() throws RecognitionException {
        Abstract_Event e = null;


        int p =0;

        Abstract_Predicate t =null;

        Abstract_Literal l =null;

        Abstract_Goal g =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:34: ( ( PLUS ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) ) | MINUS (l= literal | SHRIEK g= goal ) ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:36: ( PLUS ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) ) | MINUS (l= literal | SHRIEK g= goal ) )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:36: ( PLUS ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) ) | MINUS (l= literal | SHRIEK g= goal ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==PLUS) ) {
                alt17=1;
            }
            else if ( (LA17_0==MINUS) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:37: PLUS ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_event350); 

                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:42: ( RECEIVED OPEN p= performative ',' t= pred ')' | (l= literal | SHRIEK g= goal ) )
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==RECEIVED) ) {
                        alt15=1;
                    }
                    else if ( (LA15_0==CONST||LA15_0==NOT||LA15_0==SHRIEK||LA15_0==TRUE||LA15_0==VAR) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 0, input);

                        throw nvae;

                    }
                    switch (alt15) {
                        case 1 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:87:43: RECEIVED OPEN p= performative ',' t= pred ')'
                            {
                            match(input,RECEIVED,FOLLOW_RECEIVED_in_event353); 

                            match(input,OPEN,FOLLOW_OPEN_in_event355); 

                            pushFollow(FOLLOW_performative_in_event359);
                            p=performative();

                            state._fsp--;


                            match(input,COMMA,FOLLOW_COMMA_in_event361); 

                            pushFollow(FOLLOW_pred_in_event365);
                            t=pred();

                            state._fsp--;


                            match(input,CLOSE,FOLLOW_CLOSE_in_event367); 

                            Abstract_GMessage message = new Abstract_GMessage(new Abstract_VarTerm("From"), 
                            					new Abstract_VarTerm("To"), p, t); 
                            					e = new Abstract_Event(Abstract_Event.AILAddition, Abstract_Event.AILReceived, message);

                            }
                            break;
                        case 2 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:91:5: (l= literal | SHRIEK g= goal )
                            {
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:91:5: (l= literal | SHRIEK g= goal )
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0==CONST||LA14_0==NOT||LA14_0==TRUE||LA14_0==VAR) ) {
                                alt14=1;
                            }
                            else if ( (LA14_0==SHRIEK) ) {
                                alt14=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 14, 0, input);

                                throw nvae;

                            }
                            switch (alt14) {
                                case 1 :
                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:91:6: l= literal
                                    {
                                    pushFollow(FOLLOW_literal_in_event385);
                                    l=literal();

                                    state._fsp--;


                                    e = new Abstract_Event(Abstract_Event.AILAddition, Abstract_Event.AILBel, l);

                                    }
                                    break;
                                case 2 :
                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:92:5: SHRIEK g= goal
                                    {
                                    match(input,SHRIEK,FOLLOW_SHRIEK_in_event395); 

                                    pushFollow(FOLLOW_goal_in_event399);
                                    g=goal();

                                    state._fsp--;


                                    e = new Abstract_Event(Abstract_Event.AILAddition, g);

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:93:7: MINUS (l= literal | SHRIEK g= goal )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_event413); 

                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:93:13: (l= literal | SHRIEK g= goal )
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==CONST||LA16_0==NOT||LA16_0==TRUE||LA16_0==VAR) ) {
                        alt16=1;
                    }
                    else if ( (LA16_0==SHRIEK) ) {
                        alt16=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;

                    }
                    switch (alt16) {
                        case 1 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:93:14: l= literal
                            {
                            pushFollow(FOLLOW_literal_in_event418);
                            l=literal();

                            state._fsp--;


                            e = new Abstract_Event(Abstract_Event.AILDeletion, Abstract_Event.AILBel, l);

                            }
                            break;
                        case 2 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:94:5: SHRIEK g= goal
                            {
                            match(input,SHRIEK,FOLLOW_SHRIEK_in_event428); 

                            pushFollow(FOLLOW_goal_in_event432);
                            g=goal();

                            state._fsp--;


                            e = new Abstract_Event(Abstract_Event.AILDeletion, g);

                            }
                            break;

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return e;
    }
    // $ANTLR end "event"



    // $ANTLR start "guard_atom"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:96:1: guard_atom returns [Abstract_GuardAtom g] : ( BELIEVE l= literal | GOAL gl= goal | SENT OPEN an1= literal COMMA (an2= literal COMMA )? p= performative COMMA t= pred CLOSE |eq= equation | TRUE ) ;
    public final Abstract_GuardAtom guard_atom() throws RecognitionException {
        Abstract_GuardAtom g = null;


        Abstract_Literal l =null;

        Abstract_Goal gl =null;

        Abstract_Literal an1 =null;

        Abstract_Literal an2 =null;

        int p =0;

        Abstract_Predicate t =null;

        Abstract_Equation eq =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:96:43: ( ( BELIEVE l= literal | GOAL gl= goal | SENT OPEN an1= literal COMMA (an2= literal COMMA )? p= performative COMMA t= pred CLOSE |eq= equation | TRUE ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:96:45: ( BELIEVE l= literal | GOAL gl= goal | SENT OPEN an1= literal COMMA (an2= literal COMMA )? p= performative COMMA t= pred CLOSE |eq= equation | TRUE )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:96:45: ( BELIEVE l= literal | GOAL gl= goal | SENT OPEN an1= literal COMMA (an2= literal COMMA )? p= performative COMMA t= pred CLOSE |eq= equation | TRUE )
            int alt19=5;
            switch ( input.LA(1) ) {
            case BELIEVE:
                {
                alt19=1;
                }
                break;
            case GOAL:
                {
                alt19=2;
                }
                break;
            case SENT:
                {
                alt19=3;
                }
                break;
            case MINUS:
            case NUMBER:
            case OPEN:
            case VAR:
                {
                alt19=4;
                }
                break;
            case TRUE:
                {
                alt19=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:96:46: BELIEVE l= literal
                    {
                    match(input,BELIEVE,FOLLOW_BELIEVE_in_guard_atom453); 

                    pushFollow(FOLLOW_literal_in_guard_atom457);
                    l=literal();

                    state._fsp--;


                    g = new Abstract_GBelief(Abstract_GBelief.AILBel, l);

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:97:5: GOAL gl= goal
                    {
                    match(input,GOAL,FOLLOW_GOAL_in_guard_atom467); 

                    pushFollow(FOLLOW_goal_in_guard_atom471);
                    gl=goal();

                    state._fsp--;


                    g = new Abstract_Goal(gl);

                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:98:5: SENT OPEN an1= literal COMMA (an2= literal COMMA )? p= performative COMMA t= pred CLOSE
                    {
                    match(input,SENT,FOLLOW_SENT_in_guard_atom481); 

                    match(input,OPEN,FOLLOW_OPEN_in_guard_atom483); 

                    pushFollow(FOLLOW_literal_in_guard_atom487);
                    an1=literal();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_guard_atom489); 

                    Abstract_Literal agn = agentname;

                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:98:69: (an2= literal COMMA )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==CONST||LA18_0==NOT||LA18_0==TRUE||LA18_0==VAR) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:98:70: an2= literal COMMA
                            {
                            pushFollow(FOLLOW_literal_in_guard_atom496);
                            an2=literal();

                            state._fsp--;


                            match(input,COMMA,FOLLOW_COMMA_in_guard_atom504); 

                            agn = an2;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_performative_in_guard_atom512);
                    p=performative();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_guard_atom520); 

                    pushFollow(FOLLOW_pred_in_guard_atom524);
                    t=pred();

                    state._fsp--;


                    match(input,CLOSE,FOLLOW_CLOSE_in_guard_atom526); 

                    Abstract_GMessage mess = new Abstract_GMessage(agn, an1, p, t); 
                    								g = new Abstract_GBelief(Abstract_GBelief.AILSent, mess);

                    }
                    break;
                case 4 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:102:5: eq= equation
                    {
                    pushFollow(FOLLOW_equation_in_guard_atom540);
                    eq=equation();

                    state._fsp--;


                    g = eq;

                    }
                    break;
                case 5 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:103:5: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_guard_atom550); 

                    g = new Abstract_GBelief(Abstract_GBelief.GTrue);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return g;
    }
    // $ANTLR end "guard_atom"



    // $ANTLR start "deed"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:1: deed[ArrayList<Abstract_Deed> ds] returns [Abstract_Deed d] : ( ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action ) |wf= waitfor ) ;
    public final Abstract_Deed deed(ArrayList<Abstract_Deed> ds) throws RecognitionException {
        Abstract_Deed d = null;


        Abstract_Literal l =null;

        Abstract_Goal g =null;

        Abstract_Deed c =null;

        Abstract_Deed q =null;

        Abstract_Deed w =null;

        Abstract_Action a =null;

        Abstract_Literal wf =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:61: ( ( ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action ) |wf= waitfor ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:63: ( ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action ) |wf= waitfor )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:63: ( ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action ) |wf= waitfor )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==CALCULATE||LA24_0==CONST||LA24_0==MINUS||(LA24_0 >= NUMBER && LA24_0 <= OPEN)||LA24_0==PLUS||LA24_0==QUERYCOM||LA24_0==SEND||(LA24_0 >= UPDATE && LA24_0 <= WAIT)) ) {
                alt24=1;
            }
            else if ( (LA24_0==MULT) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:64: ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action )
                    {
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:64: ( ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) ) | UPDATE (l= literal ) | CALCULATE c= calculation[ds] | QUERYCOM q= query[ds] | WAIT w= wait[ds] |a= action )
                    int alt23=6;
                    switch ( input.LA(1) ) {
                    case PLUS:
                        {
                        alt23=1;
                        }
                        break;
                    case MINUS:
                        {
                        int LA23_2 = input.LA(2);

                        if ( (LA23_2==CONST||LA23_2==LOCK||LA23_2==NOT||LA23_2==SHRIEK||LA23_2==TRUE||LA23_2==VAR) ) {
                            alt23=1;
                        }
                        else if ( (LA23_2==NUMBER) ) {
                            alt23=6;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 23, 2, input);

                            throw nvae;

                        }
                        }
                        break;
                    case UPDATE:
                        {
                        alt23=2;
                        }
                        break;
                    case CALCULATE:
                        {
                        alt23=3;
                        }
                        break;
                    case QUERYCOM:
                        {
                        alt23=4;
                        }
                        break;
                    case WAIT:
                        {
                        alt23=5;
                        }
                        break;
                    case CONST:
                    case NUMBER:
                    case OPEN:
                    case SEND:
                    case VAR:
                        {
                        alt23=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);

                        throw nvae;

                    }

                    switch (alt23) {
                        case 1 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:65: ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) )
                            {
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:65: ( PLUS (l= literal | SHRIEK g= goal | LOCK ) | MINUS (l= literal | SHRIEK g= goal | LOCK ) )
                            int alt22=2;
                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==PLUS) ) {
                                alt22=1;
                            }
                            else if ( (LA22_0==MINUS) ) {
                                alt22=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 22, 0, input);

                                throw nvae;

                            }
                            switch (alt22) {
                                case 1 :
                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:66: PLUS (l= literal | SHRIEK g= goal | LOCK )
                                    {
                                    match(input,PLUS,FOLLOW_PLUS_in_deed574); 

                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:71: (l= literal | SHRIEK g= goal | LOCK )
                                    int alt20=3;
                                    switch ( input.LA(1) ) {
                                    case CONST:
                                    case NOT:
                                    case TRUE:
                                    case VAR:
                                        {
                                        alt20=1;
                                        }
                                        break;
                                    case SHRIEK:
                                        {
                                        alt20=2;
                                        }
                                        break;
                                    case LOCK:
                                        {
                                        alt20=3;
                                        }
                                        break;
                                    default:
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 20, 0, input);

                                        throw nvae;

                                    }

                                    switch (alt20) {
                                        case 1 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:105:72: l= literal
                                            {
                                            pushFollow(FOLLOW_literal_in_deed579);
                                            l=literal();

                                            state._fsp--;


                                            d = new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.AILBel, l);

                                            }
                                            break;
                                        case 2 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:106:5: SHRIEK g= goal
                                            {
                                            match(input,SHRIEK,FOLLOW_SHRIEK_in_deed589); 

                                            pushFollow(FOLLOW_goal_in_deed593);
                                            g=goal();

                                            state._fsp--;


                                            d = new Abstract_Deed(Abstract_Deed.AILAddition, g);

                                            }
                                            break;
                                        case 3 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:107:5: LOCK
                                            {
                                            match(input,LOCK,FOLLOW_LOCK_in_deed603); 

                                            d = new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.Dlock);

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:108:7: MINUS (l= literal | SHRIEK g= goal | LOCK )
                                    {
                                    match(input,MINUS,FOLLOW_MINUS_in_deed616); 

                                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:108:13: (l= literal | SHRIEK g= goal | LOCK )
                                    int alt21=3;
                                    switch ( input.LA(1) ) {
                                    case CONST:
                                    case NOT:
                                    case TRUE:
                                    case VAR:
                                        {
                                        alt21=1;
                                        }
                                        break;
                                    case SHRIEK:
                                        {
                                        alt21=2;
                                        }
                                        break;
                                    case LOCK:
                                        {
                                        alt21=3;
                                        }
                                        break;
                                    default:
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 21, 0, input);

                                        throw nvae;

                                    }

                                    switch (alt21) {
                                        case 1 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:108:14: l= literal
                                            {
                                            pushFollow(FOLLOW_literal_in_deed621);
                                            l=literal();

                                            state._fsp--;


                                            d = new Abstract_Deed(Abstract_Deed.AILDeletion, Abstract_Deed.AILBel, l);

                                            }
                                            break;
                                        case 2 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:109:5: SHRIEK g= goal
                                            {
                                            match(input,SHRIEK,FOLLOW_SHRIEK_in_deed631); 

                                            pushFollow(FOLLOW_goal_in_deed635);
                                            g=goal();

                                            state._fsp--;


                                            d = new Abstract_Deed(Abstract_Deed.AILDeletion, g);

                                            }
                                            break;
                                        case 3 :
                                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:110:5: LOCK
                                            {
                                            match(input,LOCK,FOLLOW_LOCK_in_deed645); 

                                            d = new Abstract_Deed(Abstract_Deed.AILDeletion, Abstract_Deed.Dlock);

                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:112:5: UPDATE (l= literal )
                            {
                            match(input,UPDATE,FOLLOW_UPDATE_in_deed662); 

                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:112:12: (l= literal )
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:112:13: l= literal
                            {
                            pushFollow(FOLLOW_literal_in_deed667);
                            l=literal();

                            state._fsp--;


                            d = new Abstract_Deed(Abstract_Deed.AILUpdate, Abstract_Deed.AILBel, l);

                            }


                            }
                            break;
                        case 3 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:113:5: CALCULATE c= calculation[ds]
                            {
                            match(input,CALCULATE,FOLLOW_CALCULATE_in_deed678); 

                            pushFollow(FOLLOW_calculation_in_deed682);
                            c=calculation(ds);

                            state._fsp--;


                            d = c;

                            }
                            break;
                        case 4 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:114:5: QUERYCOM q= query[ds]
                            {
                            match(input,QUERYCOM,FOLLOW_QUERYCOM_in_deed693); 

                            pushFollow(FOLLOW_query_in_deed697);
                            q=query(ds);

                            state._fsp--;


                            d = q;

                            }
                            break;
                        case 5 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:115:5: WAIT w= wait[ds]
                            {
                            match(input,WAIT,FOLLOW_WAIT_in_deed708); 

                            pushFollow(FOLLOW_wait_in_deed712);
                            w=wait(ds);

                            state._fsp--;


                            d = w;

                            }
                            break;
                        case 6 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:116:5: a= action
                            {
                            pushFollow(FOLLOW_action_in_deed725);
                            a=action();

                            state._fsp--;


                            d = new Abstract_Deed(a);

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:117:5: wf= waitfor
                    {
                    pushFollow(FOLLOW_waitfor_in_deed738);
                    wf=waitfor();

                    state._fsp--;


                    d = new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.Dwaitfor, wf);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return d;
    }
    // $ANTLR end "deed"



    // $ANTLR start "calculation"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:121:1: calculation[ArrayList<Abstract_Deed> ds] returns [Abstract_Deed d] : OPEN l1= literal COMMA v= var CLOSE ;
    public final Abstract_Deed calculation(ArrayList<Abstract_Deed> ds) throws RecognitionException {
        Abstract_Deed d = null;


        Abstract_Literal l1 =null;

        Abstract_VarTerm v =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:121:68: ( OPEN l1= literal COMMA v= var CLOSE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:121:70: OPEN l1= literal COMMA v= var CLOSE
            {
            match(input,OPEN,FOLLOW_OPEN_in_calculation768); 

            pushFollow(FOLLOW_literal_in_calculation774);
            l1=literal();

            state._fsp--;


            match(input,COMMA,FOLLOW_COMMA_in_calculation776); 

            pushFollow(FOLLOW_var_in_calculation780);
            v=var();

            state._fsp--;


            match(input,CLOSE,FOLLOW_CLOSE_in_calculation782); 

            Abstract_Action a = new Abstract_Action("calculate"); a.addTerm(l1); a.addTerm(new Abstract_VarTerm("NewVarForCalculate")); ds.add(new Abstract_Deed(a));
            	Abstract_Literal wf = new Abstract_Literal("result"); wf.addTerm(l1); wf.addTerm(v); ds.add(new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.Dwaitfor, wf));
            	Abstract_Action rs = new Abstract_Action("remove_shared"); rs.addTerm(wf); d = new Abstract_Deed(rs);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return d;
    }
    // $ANTLR end "calculation"



    // $ANTLR start "query"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:126:1: query[ArrayList<Abstract_Deed> ds] returns [Abstract_Deed d] : OPEN l1= literal CLOSE ;
    public final Abstract_Deed query(ArrayList<Abstract_Deed> ds) throws RecognitionException {
        Abstract_Deed d = null;


        Abstract_Literal l1 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:126:62: ( OPEN l1= literal CLOSE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:126:64: OPEN l1= literal CLOSE
            {
            match(input,OPEN,FOLLOW_OPEN_in_query800); 

            pushFollow(FOLLOW_literal_in_query806);
            l1=literal();

            state._fsp--;


            match(input,CLOSE,FOLLOW_CLOSE_in_query809); 

            Abstract_Action a = new Abstract_Action("query"); a.addTerm(l1); ds.add(new Abstract_Deed(a));
            	ds.add(new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.Dwaitfor, l1));
            	Abstract_Action rs = new Abstract_Action("remove_shared"); rs.addTerm(l1); d = new Abstract_Deed(rs);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return d;
    }
    // $ANTLR end "query"



    // $ANTLR start "wait"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:131:1: wait[ArrayList<Abstract_Deed> ds] returns [Abstract_Deed d] : OPEN l1= term COMMA l2= literal CLOSE ;
    public final Abstract_Deed wait(ArrayList<Abstract_Deed> ds) throws RecognitionException {
        Abstract_Deed d = null;


        Abstract_Term l1 =null;

        Abstract_Literal l2 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:131:61: ( OPEN l1= term COMMA l2= literal CLOSE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:131:63: OPEN l1= term COMMA l2= literal CLOSE
            {
            match(input,OPEN,FOLLOW_OPEN_in_wait827); 

            pushFollow(FOLLOW_term_in_wait833);
            l1=term();

            state._fsp--;


            match(input,COMMA,FOLLOW_COMMA_in_wait835); 

            pushFollow(FOLLOW_literal_in_wait839);
            l2=literal();

            state._fsp--;


            match(input,CLOSE,FOLLOW_CLOSE_in_wait841); 

            Abstract_Action a = new Abstract_Action("wait"); a.addTerm(l1); a.addTerm(l2); ds.add(new Abstract_Deed(a));
            	Abstract_Literal wf = new Abstract_Literal("waited"); wf.addTerm(l2); ds.add(new Abstract_Deed(Abstract_Deed.AILAddition, Abstract_Deed.Dwaitfor, wf));
            	Abstract_Action rs = new Abstract_Action("remove_shared"); rs.addTerm(wf); d = new Abstract_Deed(rs);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return d;
    }
    // $ANTLR end "wait"



    // $ANTLR start "brule"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:1: brule returns [Abstract_Rule r] : head= guard_atom ( BRULEARROW f= logicalfmla SEMI | SEMI ) ;
    public final Abstract_Rule brule() throws RecognitionException {
        Abstract_Rule r = null;


        Abstract_GuardAtom head =null;

        Abstract_LogicalFormula f =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:33: (head= guard_atom ( BRULEARROW f= logicalfmla SEMI | SEMI ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:35: head= guard_atom ( BRULEARROW f= logicalfmla SEMI | SEMI )
            {
            pushFollow(FOLLOW_guard_atom_in_brule863);
            head=guard_atom();

            state._fsp--;


            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:51: ( BRULEARROW f= logicalfmla SEMI | SEMI )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==BRULEARROW) ) {
                alt25=1;
            }
            else if ( (LA25_0==SEMI) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:52: BRULEARROW f= logicalfmla SEMI
                    {
                    match(input,BRULEARROW,FOLLOW_BRULEARROW_in_brule866); 

                    pushFollow(FOLLOW_logicalfmla_in_brule870);
                    f=logicalfmla();

                    state._fsp--;


                    r = new Abstract_Rule(head, f);

                    match(input,SEMI,FOLLOW_SEMI_in_brule874); 

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:136:125: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_brule878); 

                    r = new Abstract_Rule(head);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return r;
    }
    // $ANTLR end "brule"



    // $ANTLR start "logicalfmla"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:138:1: logicalfmla returns [Abstract_LogicalFormula f] : (n= notfmla |a= andfmla );
    public final Abstract_LogicalFormula logicalfmla() throws RecognitionException {
        Abstract_LogicalFormula f = null;


        Abstract_LogicalFormula n =null;

        Abstract_LogicalFormula a =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:138:49: (n= notfmla |a= andfmla )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NOT) ) {
                alt26=1;
            }
            else if ( (LA26_0==BELIEVE||LA26_0==GOAL||LA26_0==MINUS||(LA26_0 >= NUMBER && LA26_0 <= OPEN)||LA26_0==SENT||LA26_0==TRUE||LA26_0==VAR) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:138:51: n= notfmla
                    {
                    pushFollow(FOLLOW_notfmla_in_logicalfmla895);
                    n=notfmla();

                    state._fsp--;


                    f = n;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:138:75: a= andfmla
                    {
                    pushFollow(FOLLOW_andfmla_in_logicalfmla902);
                    a=andfmla();

                    state._fsp--;


                    f = a;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "logicalfmla"



    // $ANTLR start "notfmla"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:139:1: notfmla returns [Abstract_LogicalFormula f] : NOT OPEN lf= logicalfmla CLOSE ;
    public final Abstract_LogicalFormula notfmla() throws RecognitionException {
        Abstract_LogicalFormula f = null;


        Abstract_LogicalFormula lf =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:139:45: ( NOT OPEN lf= logicalfmla CLOSE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:139:47: NOT OPEN lf= logicalfmla CLOSE
            {
            match(input,NOT,FOLLOW_NOT_in_notfmla915); 

            match(input,OPEN,FOLLOW_OPEN_in_notfmla917); 

            pushFollow(FOLLOW_logicalfmla_in_notfmla923);
            lf=logicalfmla();

            state._fsp--;


            f = new Abstract_LogExpr(Abstract_LogExpr.not, lf);

            match(input,CLOSE,FOLLOW_CLOSE_in_notfmla927); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "notfmla"



    // $ANTLR start "andfmla"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:140:1: andfmla returns [Abstract_LogicalFormula f] : gb= guard_atom ( COMMA and= logicalfmla )? ;
    public final Abstract_LogicalFormula andfmla() throws RecognitionException {
        Abstract_LogicalFormula f = null;


        Abstract_GuardAtom gb =null;

        Abstract_LogicalFormula and =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:140:45: (gb= guard_atom ( COMMA and= logicalfmla )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:140:47: gb= guard_atom ( COMMA and= logicalfmla )?
            {
            pushFollow(FOLLOW_guard_atom_in_andfmla942);
            gb=guard_atom();

            state._fsp--;


            f = gb;

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:140:77: ( COMMA and= logicalfmla )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==COMMA) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:140:78: COMMA and= logicalfmla
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_andfmla947); 

                    pushFollow(FOLLOW_logicalfmla_in_andfmla951);
                    and=logicalfmla();

                    state._fsp--;


                    f = new Abstract_LogExpr(gb, Abstract_LogExpr.and, and);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "andfmla"



    // $ANTLR start "waitfor"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:142:1: waitfor returns [Abstract_Literal wf] : MULT l= literal ;
    public final Abstract_Literal waitfor() throws RecognitionException {
        Abstract_Literal wf = null;


        Abstract_Literal l =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:142:39: ( MULT l= literal )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:142:42: MULT l= literal
            {
            match(input,MULT,FOLLOW_MULT_in_waitfor969); 

            pushFollow(FOLLOW_literal_in_waitfor973);
            l=literal();

            state._fsp--;


            wf = l;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return wf;
    }
    // $ANTLR end "waitfor"



    // $ANTLR start "action"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:144:1: action returns [Abstract_Action a] : ( (a1= atom PLUS a2= atom EQ_ASSGN v= var ) | ( SEND OPEN an= literal COMMA p= performative COMMA t= pred CLOSE ) |t= pred );
    public final Abstract_Action action() throws RecognitionException {
        Abstract_Action a = null;


        Abstract_NumberTerm a1 =null;

        Abstract_NumberTerm a2 =null;

        Abstract_VarTerm v =null;

        Abstract_Literal an =null;

        int p =0;

        Abstract_Predicate t =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:144:36: ( (a1= atom PLUS a2= atom EQ_ASSGN v= var ) | ( SEND OPEN an= literal COMMA p= performative COMMA t= pred CLOSE ) |t= pred )
            int alt28=3;
            switch ( input.LA(1) ) {
            case MINUS:
            case NUMBER:
            case OPEN:
                {
                alt28=1;
                }
                break;
            case VAR:
                {
                int LA28_3 = input.LA(2);

                if ( (LA28_3==PLUS) ) {
                    alt28=1;
                }
                else if ( (LA28_3==COMMA||LA28_3==SEMI) ) {
                    alt28=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 28, 3, input);

                    throw nvae;

                }
                }
                break;
            case SEND:
                {
                alt28=2;
                }
                break;
            case CONST:
                {
                alt28=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }

            switch (alt28) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:145:2: (a1= atom PLUS a2= atom EQ_ASSGN v= var )
                    {
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:145:2: (a1= atom PLUS a2= atom EQ_ASSGN v= var )
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:145:3: a1= atom PLUS a2= atom EQ_ASSGN v= var
                    {
                    pushFollow(FOLLOW_atom_in_action992);
                    a1=atom();

                    state._fsp--;


                    match(input,PLUS,FOLLOW_PLUS_in_action994); 

                    pushFollow(FOLLOW_atom_in_action998);
                    a2=atom();

                    state._fsp--;


                    match(input,EQ_ASSGN,FOLLOW_EQ_ASSGN_in_action1000); 

                    pushFollow(FOLLOW_var_in_action1004);
                    v=var();

                    state._fsp--;


                    a = new Abstract_Action("sum"); a.addTerm(a1); a.addTerm(a2); a.addTerm(v); 

                    }


                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:146:2: ( SEND OPEN an= literal COMMA p= performative COMMA t= pred CLOSE )
                    {
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:146:2: ( SEND OPEN an= literal COMMA p= performative COMMA t= pred CLOSE )
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:146:3: SEND OPEN an= literal COMMA p= performative COMMA t= pred CLOSE
                    {
                    match(input,SEND,FOLLOW_SEND_in_action1013); 

                    match(input,OPEN,FOLLOW_OPEN_in_action1015); 

                    pushFollow(FOLLOW_literal_in_action1019);
                    an=literal();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_action1021); 

                    pushFollow(FOLLOW_performative_in_action1025);
                    p=performative();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_action1027); 

                    pushFollow(FOLLOW_pred_in_action1031);
                    t=pred();

                    state._fsp--;


                    match(input,CLOSE,FOLLOW_CLOSE_in_action1033); 

                    a = new Abstract_SendAction(an, p, t);

                    }


                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:147:2: t= pred
                    {
                    pushFollow(FOLLOW_pred_in_action1044);
                    t=pred();

                    state._fsp--;


                    a = new Abstract_Action(t, Abstract_Action.normalAction);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return a;
    }
    // $ANTLR end "action"



    // $ANTLR start "performative"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:1: performative returns [int b] : ( TELL | PERFORM | ACHIEVE ) ;
    public final int performative() throws RecognitionException {
        int b = 0;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:30: ( ( TELL | PERFORM | ACHIEVE ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:32: ( TELL | PERFORM | ACHIEVE )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:32: ( TELL | PERFORM | ACHIEVE )
            int alt29=3;
            switch ( input.LA(1) ) {
            case TELL:
                {
                alt29=1;
                }
                break;
            case PERFORM:
                {
                alt29=2;
                }
                break;
            case ACHIEVE:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:33: TELL
                    {
                    match(input,TELL,FOLLOW_TELL_in_performative1059); 

                    b =1;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:48: PERFORM
                    {
                    match(input,PERFORM,FOLLOW_PERFORM_in_performative1065); 

                    b =2;

                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:149:66: ACHIEVE
                    {
                    match(input,ACHIEVE,FOLLOW_ACHIEVE_in_performative1071); 

                    b = 3;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return b;
    }
    // $ANTLR end "performative"



    // $ANTLR start "environment"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:185:1: environment returns [String env] : w= classpath ;
    public final String environment() throws RecognitionException {
        String env = null;


        String w =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:185:34: (w= classpath )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:185:36: w= classpath
            {
            pushFollow(FOLLOW_classpath_in_environment1320);
            w=classpath();

            state._fsp--;


            env = w;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return env;
    }
    // $ANTLR end "environment"



    // $ANTLR start "classpath"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:186:1: classpath returns [String s] : w= word ( POINT w1= word )+ ;
    public final String classpath() throws RecognitionException {
        String s = null;


        String w =null;

        String w1 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:186:30: (w= word ( POINT w1= word )+ )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:186:32: w= word ( POINT w1= word )+
            {
            pushFollow(FOLLOW_word_in_classpath1335);
            w=word();

            state._fsp--;


            s = w;

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:186:52: ( POINT w1= word )+
            int cnt30=0;
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==POINT) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:186:53: POINT w1= word
            	    {
            	    match(input,POINT,FOLLOW_POINT_in_classpath1340); 

            	    pushFollow(FOLLOW_word_in_classpath1344);
            	    w1=word();

            	    state._fsp--;


            	    s+="."; s+=w1;

            	    }
            	    break;

            	default :
            	    if ( cnt30 >= 1 ) break loop30;
                        EarlyExitException eee =
                            new EarlyExitException(30, input);
                        throw eee;
                }
                cnt30++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "classpath"



    // $ANTLR start "word"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:1: word returns [String s] : ( CONST | VAR ) ;
    public final String word() throws RecognitionException {
        String s = null;


        Token CONST1=null;
        Token VAR2=null;

        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:25: ( ( CONST | VAR ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:27: ( CONST | VAR )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:27: ( CONST | VAR )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==CONST) ) {
                alt31=1;
            }
            else if ( (LA31_0==VAR) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:28: CONST
                    {
                    CONST1=(Token)match(input,CONST,FOLLOW_CONST_in_word1445); 

                    s =CONST1.getText();

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:187:59: VAR
                    {
                    VAR2=(Token)match(input,VAR,FOLLOW_VAR_in_word1451); 

                    s =VAR2.getText();

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "word"



    // $ANTLR start "agentnameterm"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:189:1: agentnameterm returns [Abstract_StringTerm s] : ( CONST |v= var );
    public final Abstract_StringTerm agentnameterm() throws RecognitionException {
        Abstract_StringTerm s = null;


        Token CONST3=null;
        Abstract_VarTerm v =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:189:47: ( CONST |v= var )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==CONST) ) {
                alt32=1;
            }
            else if ( (LA32_0==VAR) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:189:49: CONST
                    {
                    CONST3=(Token)match(input,CONST,FOLLOW_CONST_in_agentnameterm1551); 

                    s = new Abstract_StringTermImpl(CONST3.getText());

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:189:111: v= var
                    {
                    pushFollow(FOLLOW_var_in_agentnameterm1559);
                    v=var();

                    state._fsp--;


                    s = v;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "agentnameterm"



    // $ANTLR start "literal"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:192:1: literal returns [Abstract_Literal l] : ( ( TRUE | NOT nt= pred ) |t= pred );
    public final Abstract_Literal literal() throws RecognitionException {
        Abstract_Literal l = null;


        Abstract_Predicate nt =null;

        Abstract_Predicate t =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:192:37: ( ( TRUE | NOT nt= pred ) |t= pred )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==NOT||LA34_0==TRUE) ) {
                alt34=1;
            }
            else if ( (LA34_0==CONST||LA34_0==VAR) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:192:40: ( TRUE | NOT nt= pred )
                    {
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:192:40: ( TRUE | NOT nt= pred )
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==TRUE) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==NOT) ) {
                        alt33=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;

                    }
                    switch (alt33) {
                        case 1 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:192:41: TRUE
                            {
                            match(input,TRUE,FOLLOW_TRUE_in_literal1575); 

                            l = new Abstract_Literal(Abstract_Literal.LTrue);

                            }
                            break;
                        case 2 :
                            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:193:5: NOT nt= pred
                            {
                            match(input,NOT,FOLLOW_NOT_in_literal1586); 

                            pushFollow(FOLLOW_pred_in_literal1590);
                            nt=pred();

                            state._fsp--;



                            				if (nt instanceof Abstract_VarTerm) 
                            					{l = (Abstract_VarTerm) nt; l.setNegated(false);}
                            					else { l = new Abstract_Literal(Abstract_Literal.LNeg, new Abstract_Pred(nt));}

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:197:5: t= pred
                    {
                    pushFollow(FOLLOW_pred_in_literal1604);
                    t=pred();

                    state._fsp--;


                    if (t instanceof Abstract_VarTerm) 
                    				            {l = (Abstract_VarTerm) t;} 
                    				            else {l = new Abstract_Literal(Abstract_Literal.LPos, new Abstract_Pred(t));}

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return l;
    }
    // $ANTLR end "literal"



    // $ANTLR start "pred"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:201:1: pred returns [Abstract_Predicate t] : (v= var |f= function );
    public final Abstract_Predicate pred() throws RecognitionException {
        Abstract_Predicate t = null;


        Abstract_VarTerm v =null;

        Abstract_Predicate f =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:201:37: (v= var |f= function )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==VAR) ) {
                alt35=1;
            }
            else if ( (LA35_0==CONST) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:201:39: v= var
                    {
                    pushFollow(FOLLOW_var_in_pred1620);
                    v=var();

                    state._fsp--;


                    t = v;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:201:59: f= function
                    {
                    pushFollow(FOLLOW_function_in_pred1627);
                    f=function();

                    state._fsp--;


                    t = f;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return t;
    }
    // $ANTLR end "pred"



    // $ANTLR start "function"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:202:1: function returns [Abstract_Predicate f] : CONST ( OPEN terms[$f] CLOSE )? ;
    public final Abstract_Predicate function() throws RecognitionException {
        Abstract_Predicate f = null;


        Token CONST4=null;

        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:202:40: ( CONST ( OPEN terms[$f] CLOSE )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:202:42: CONST ( OPEN terms[$f] CLOSE )?
            {
            CONST4=(Token)match(input,CONST,FOLLOW_CONST_in_function1639); 

            f = new Abstract_Predicate(CONST4.getText());

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:202:97: ( OPEN terms[$f] CLOSE )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==OPEN) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:202:98: OPEN terms[$f] CLOSE
                    {
                    match(input,OPEN,FOLLOW_OPEN_in_function1644); 

                    pushFollow(FOLLOW_terms_in_function1646);
                    terms(f);

                    state._fsp--;


                    match(input,CLOSE,FOLLOW_CLOSE_in_function1649); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return f;
    }
    // $ANTLR end "function"



    // $ANTLR start "terms"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:204:1: terms[Abstract_Predicate f] : t= term ( COMMA terms[$f] )? ;
    public final void terms(Abstract_Predicate f) throws RecognitionException {
        Abstract_Term t =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:204:29: (t= term ( COMMA terms[$f] )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:204:31: t= term ( COMMA terms[$f] )?
            {
            pushFollow(FOLLOW_term_in_terms1662);
            t=term();

            state._fsp--;


            f.addTerm(t);

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:204:58: ( COMMA terms[$f] )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==COMMA) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:204:59: COMMA terms[$f]
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_terms1667); 

                    pushFollow(FOLLOW_terms_in_terms1669);
                    terms(f);

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "terms"



    // $ANTLR start "term"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:205:1: term returns [Abstract_Term t] : (a= atom |s= stringterm |f= function );
    public final Abstract_Term term() throws RecognitionException {
        Abstract_Term t = null;


        Abstract_NumberTerm a =null;

        Abstract_StringTerm s =null;

        Abstract_Predicate f =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:205:31: (a= atom |s= stringterm |f= function )
            int alt38=3;
            switch ( input.LA(1) ) {
            case MINUS:
            case NUMBER:
            case OPEN:
            case VAR:
                {
                alt38=1;
                }
                break;
            case DOUBLEQUOTE:
                {
                alt38=2;
                }
                break;
            case CONST:
                {
                alt38=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }

            switch (alt38) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:205:34: a= atom
                    {
                    pushFollow(FOLLOW_atom_in_term1688);
                    a=atom();

                    state._fsp--;


                    t = a;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:205:58: s= stringterm
                    {
                    pushFollow(FOLLOW_stringterm_in_term1698);
                    s=stringterm();

                    state._fsp--;


                    t = s;

                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:205:88: f= function
                    {
                    pushFollow(FOLLOW_function_in_term1706);
                    f=function();

                    state._fsp--;


                    t = f;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return t;
    }
    // $ANTLR end "term"



    // $ANTLR start "atom"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:207:1: atom returns [Abstract_NumberTerm t] : (n= numberstring |v= var | OPEN a= arithexpr CLOSE );
    public final Abstract_NumberTerm atom() throws RecognitionException {
        Abstract_NumberTerm t = null;


        String n =null;

        Abstract_VarTerm v =null;

        Abstract_NumberTerm a =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:207:38: (n= numberstring |v= var | OPEN a= arithexpr CLOSE )
            int alt39=3;
            switch ( input.LA(1) ) {
            case MINUS:
            case NUMBER:
                {
                alt39=1;
                }
                break;
            case VAR:
                {
                alt39=2;
                }
                break;
            case OPEN:
                {
                alt39=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;

            }

            switch (alt39) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:207:40: n= numberstring
                    {
                    pushFollow(FOLLOW_numberstring_in_atom1724);
                    n=numberstring();

                    state._fsp--;


                    t = new Abstract_NumberTermImpl(n);

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:208:6: v= var
                    {
                    pushFollow(FOLLOW_var_in_atom1737);
                    v=var();

                    state._fsp--;


                    t = v;

                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:208:27: OPEN a= arithexpr CLOSE
                    {
                    match(input,OPEN,FOLLOW_OPEN_in_atom1743); 

                    pushFollow(FOLLOW_arithexpr_in_atom1747);
                    a=arithexpr();

                    state._fsp--;


                    match(input,CLOSE,FOLLOW_CLOSE_in_atom1749); 

                    t = a;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return t;
    }
    // $ANTLR end "atom"



    // $ANTLR start "stringterm"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:209:1: stringterm returns [Abstract_StringTerm s] : DOUBLEQUOTE STRING DOUBLEQUOTE ;
    public final Abstract_StringTerm stringterm() throws RecognitionException {
        Abstract_StringTerm s = null;


        Token STRING5=null;

        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:209:44: ( DOUBLEQUOTE STRING DOUBLEQUOTE )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:209:46: DOUBLEQUOTE STRING DOUBLEQUOTE
            {
            match(input,DOUBLEQUOTE,FOLLOW_DOUBLEQUOTE_in_stringterm1762); 

            STRING5=(Token)match(input,STRING,FOLLOW_STRING_in_stringterm1765); 

            match(input,DOUBLEQUOTE,FOLLOW_DOUBLEQUOTE_in_stringterm1767); 

            s = new Abstract_StringTermImpl(STRING5.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "stringterm"



    // $ANTLR start "var"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:211:1: var returns [Abstract_VarTerm v] : VAR ;
    public final Abstract_VarTerm var() throws RecognitionException {
        Abstract_VarTerm v = null;


        Token VAR6=null;

        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:211:34: ( VAR )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:211:36: VAR
            {
            VAR6=(Token)match(input,VAR,FOLLOW_VAR_in_var1781); 


            	if (variables.containsKey(VAR6.getText())) {
            		v = variables.get(VAR6.getText());
            		} else {
            		v = new Abstract_VarTerm(VAR6.getText());
            		variables.put(VAR6.getText(), v);
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return v;
    }
    // $ANTLR end "var"



    // $ANTLR start "numberstring"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:1: numberstring returns [String s] : ( MINUS )? (n1= NUMBER ( POINT n2= NUMBER )? ) ;
    public final String numberstring() throws RecognitionException {
        String s = null;


        Token n1=null;
        Token n2=null;

        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:33: ( ( MINUS )? (n1= NUMBER ( POINT n2= NUMBER )? ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:35: ( MINUS )? (n1= NUMBER ( POINT n2= NUMBER )? )
            {
            s = "";

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:46: ( MINUS )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==MINUS) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:47: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_numberstring1798); 

                    s += "-";

                    }
                    break;

            }


            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:68: (n1= NUMBER ( POINT n2= NUMBER )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:220:69: n1= NUMBER ( POINT n2= NUMBER )?
            {
            n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_numberstring1807); 

            s += n1.getText();

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:221:6: ( POINT n2= NUMBER )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==POINT) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:221:7: POINT n2= NUMBER
                    {
                    match(input,POINT,FOLLOW_POINT_in_numberstring1817); 

                    s += ".";

                    n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_numberstring1823); 

                    s += n2.getText();

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return s;
    }
    // $ANTLR end "numberstring"



    // $ANTLR start "equation"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:222:1: equation returns [Abstract_Equation eq] : a1= arithexpr oper= eqoper a2= arithexpr ;
    public final Abstract_Equation equation() throws RecognitionException {
        Abstract_Equation eq = null;


        Abstract_NumberTerm a1 =null;

        int oper =0;

        Abstract_NumberTerm a2 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:222:40: (a1= arithexpr oper= eqoper a2= arithexpr )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:222:42: a1= arithexpr oper= eqoper a2= arithexpr
            {
            pushFollow(FOLLOW_arithexpr_in_equation1840);
            a1=arithexpr();

            state._fsp--;


            pushFollow(FOLLOW_eqoper_in_equation1844);
            oper=eqoper();

            state._fsp--;


            pushFollow(FOLLOW_arithexpr_in_equation1848);
            a2=arithexpr();

            state._fsp--;


            eq = new Abstract_Equation(a1, oper, a2);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eq;
    }
    // $ANTLR end "equation"



    // $ANTLR start "eqoper"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:223:1: eqoper returns [int oper] : ( LESS | EQ );
    public final int eqoper() throws RecognitionException {
        int oper = 0;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:223:27: ( LESS | EQ )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LESS) ) {
                alt42=1;
            }
            else if ( (LA42_0==EQ) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:223:29: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_eqoper1862); 

                    oper =Abstract_Equation.less;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:223:68: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_eqoper1868); 

                    oper =Abstract_Equation.equal;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return oper;
    }
    // $ANTLR end "eqoper"



    // $ANTLR start "arithexpr"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:225:1: arithexpr returns [Abstract_NumberTerm t] : m= multexpr (oper= addoper m1= multexpr )? ;
    public final Abstract_NumberTerm arithexpr() throws RecognitionException {
        Abstract_NumberTerm t = null;


        Abstract_NumberTerm m =null;

        int oper =0;

        Abstract_NumberTerm m1 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:225:43: (m= multexpr (oper= addoper m1= multexpr )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:225:45: m= multexpr (oper= addoper m1= multexpr )?
            {
            pushFollow(FOLLOW_multexpr_in_arithexpr1884);
            m=multexpr();

            state._fsp--;


            t = m;

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:225:69: (oper= addoper m1= multexpr )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==MINUS||LA43_0==PLUS) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:225:71: oper= addoper m1= multexpr
                    {
                    pushFollow(FOLLOW_addoper_in_arithexpr1892);
                    oper=addoper();

                    state._fsp--;


                    pushFollow(FOLLOW_multexpr_in_arithexpr1896);
                    m1=multexpr();

                    state._fsp--;


                    t = new Abstract_ArithExpr(m, oper, m1);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return t;
    }
    // $ANTLR end "arithexpr"



    // $ANTLR start "multexpr"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:226:1: multexpr returns [Abstract_NumberTerm t] : a= atom (oper= multoper a1= atom )? ;
    public final Abstract_NumberTerm multexpr() throws RecognitionException {
        Abstract_NumberTerm t = null;


        Abstract_NumberTerm a =null;

        int oper =0;

        Abstract_NumberTerm a1 =null;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:226:42: (a= atom (oper= multoper a1= atom )? )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:226:44: a= atom (oper= multoper a1= atom )?
            {
            pushFollow(FOLLOW_atom_in_multexpr1913);
            a=atom();

            state._fsp--;


            t = a;

            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:226:64: (oper= multoper a1= atom )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==DIV||(LA44_0 >= MOD && LA44_0 <= MULT)) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:226:65: oper= multoper a1= atom
                    {
                    pushFollow(FOLLOW_multoper_in_multexpr1920);
                    oper=multoper();

                    state._fsp--;


                    pushFollow(FOLLOW_atom_in_multexpr1924);
                    a1=atom();

                    state._fsp--;


                    t = new Abstract_ArithExpr(a, oper, a1);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return t;
    }
    // $ANTLR end "multexpr"



    // $ANTLR start "addoper"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:1: addoper returns [int oper] : ( PLUS | MINUS ) ;
    public final int addoper() throws RecognitionException {
        int oper = 0;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:28: ( ( PLUS | MINUS ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:30: ( PLUS | MINUS )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:30: ( PLUS | MINUS )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==PLUS) ) {
                alt45=1;
            }
            else if ( (LA45_0==MINUS) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:31: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_addoper1941); 

                    oper =Abstract_ArithExpr.plus;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:228:70: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_addoper1946); 

                    oper =Abstract_ArithExpr.minus;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return oper;
    }
    // $ANTLR end "addoper"



    // $ANTLR start "multoper"
    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:1: multoper returns [int oper] : ( MULT | DIV | MOD ) ;
    public final int multoper() throws RecognitionException {
        int oper = 0;


        try {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:29: ( ( MULT | DIV | MOD ) )
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:31: ( MULT | DIV | MOD )
            {
            // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:31: ( MULT | DIV | MOD )
            int alt46=3;
            switch ( input.LA(1) ) {
            case MULT:
                {
                alt46=1;
                }
                break;
            case DIV:
                {
                alt46=2;
                }
                break;
            case MOD:
                {
                alt46=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }

            switch (alt46) {
                case 1 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:32: MULT
                    {
                    match(input,MULT,FOLLOW_MULT_in_multoper1961); 

                    oper =Abstract_ArithExpr.times;

                    }
                    break;
                case 2 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:73: DIV
                    {
                    match(input,DIV,FOLLOW_DIV_in_multoper1967); 

                    oper =Abstract_ArithExpr.div;

                    }
                    break;
                case 3 :
                    // /Users/lad/Eclipse/ajpf/src/classes/eass/parser/EASS.g:229:111: MOD
                    {
                    match(input,MOD,FOLLOW_MOD_in_multoper1973); 

                    oper =Abstract_ArithExpr.mod;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return oper;
    }
    // $ANTLR end "multoper"

    // Delegated rules


 

    public static final BitSet FOLLOW_eassagents_in_mas89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EASS_in_eassagents102 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_eassagent_in_eassagents111 = new BitSet(new long[]{0x0000000100000012L});
    public static final BitSet FOLLOW_NAME_in_eassagent130 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_word_in_eassagent134 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ABSTRACTION_in_eassagent141 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_word_in_eassagent145 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_BELIEFS_in_eassagent157 = new BitSet(new long[]{0x0140000402010080L});
    public static final BitSet FOLLOW_literal_in_eassagent162 = new BitSet(new long[]{0x0140000402010080L});
    public static final BitSet FOLLOW_BELIEFRULES_in_eassagent170 = new BitSet(new long[]{0x0141001823000200L});
    public static final BitSet FOLLOW_brule_in_eassagent175 = new BitSet(new long[]{0x0141001823000200L});
    public static final BitSet FOLLOW_GOALS_in_eassagent184 = new BitSet(new long[]{0x0140008400010000L});
    public static final BitSet FOLLOW_goal_in_eassagent189 = new BitSet(new long[]{0x0140008400010000L});
    public static final BitSet FOLLOW_PLANS_in_eassagent196 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_plan_in_eassagent201 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_literal_in_goal221 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_SQOPEN_in_goal223 = new BitSet(new long[]{0x0000004000000040L});
    public static final BitSet FOLLOW_ACHIEVEGOAL_in_goal226 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_PERFORMGOAL_in_goal234 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_SQCLOSE_in_goal239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_event_in_plan255 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_plan263 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_CURLYOPEN_in_plan265 = new BitSet(new long[]{0x0141001C21000200L});
    public static final BitSet FOLLOW_NOT_in_plan270 = new BitSet(new long[]{0x0141001821000200L});
    public static final BitSet FOLLOW_guard_atom_in_plan278 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_COMMA_in_plan286 = new BitSet(new long[]{0x0141001C21000200L});
    public static final BitSet FOLLOW_NOT_in_plan291 = new BitSet(new long[]{0x0141001821000200L});
    public static final BitSet FOLLOW_guard_atom_in_plan299 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_CURLYCLOSE_in_plan305 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_RULEARROW_in_plan308 = new BitSet(new long[]{0x03808918A0010800L});
    public static final BitSet FOLLOW_deed_in_plan313 = new BitSet(new long[]{0x0000400000004000L});
    public static final BitSet FOLLOW_COMMA_in_plan319 = new BitSet(new long[]{0x03808918A0010800L});
    public static final BitSet FOLLOW_deed_in_plan323 = new BitSet(new long[]{0x0000400000004000L});
    public static final BitSet FOLLOW_SEMI_in_plan334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_event350 = new BitSet(new long[]{0x0142100400010000L});
    public static final BitSet FOLLOW_RECEIVED_in_event353 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_OPEN_in_event355 = new BitSet(new long[]{0x0020002000000020L});
    public static final BitSet FOLLOW_performative_in_event359 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_event361 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_pred_in_event365 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_event367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_event385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHRIEK_in_event395 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_goal_in_event399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_event413 = new BitSet(new long[]{0x0142000400010000L});
    public static final BitSet FOLLOW_literal_in_event418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHRIEK_in_event428 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_goal_in_event432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BELIEVE_in_guard_atom453 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_guard_atom457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GOAL_in_guard_atom467 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_goal_in_guard_atom471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SENT_in_guard_atom481 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_OPEN_in_guard_atom483 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_guard_atom487 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_guard_atom489 = new BitSet(new long[]{0x0160002400010020L});
    public static final BitSet FOLLOW_literal_in_guard_atom496 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_guard_atom504 = new BitSet(new long[]{0x0020002000000020L});
    public static final BitSet FOLLOW_performative_in_guard_atom512 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_guard_atom520 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_pred_in_guard_atom524 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_guard_atom526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equation_in_guard_atom540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_guard_atom550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_deed574 = new BitSet(new long[]{0x0142000410010000L});
    public static final BitSet FOLLOW_literal_in_deed579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHRIEK_in_deed589 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_goal_in_deed593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCK_in_deed603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_deed616 = new BitSet(new long[]{0x0142000410010000L});
    public static final BitSet FOLLOW_literal_in_deed621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHRIEK_in_deed631 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_goal_in_deed635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCK_in_deed645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPDATE_in_deed662 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_deed667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATE_in_deed678 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_calculation_in_deed682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUERYCOM_in_deed693 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_query_in_deed697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WAIT_in_deed708 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_wait_in_deed712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_action_in_deed725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_waitfor_in_deed738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_in_calculation768 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_calculation774 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_calculation776 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_var_in_calculation780 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_calculation782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_in_query800 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_query806 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_query809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_in_wait827 = new BitSet(new long[]{0x0100001820110000L});
    public static final BitSet FOLLOW_term_in_wait833 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_wait835 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_wait839 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_wait841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_guard_atom_in_brule863 = new BitSet(new long[]{0x0000400000000400L});
    public static final BitSet FOLLOW_BRULEARROW_in_brule866 = new BitSet(new long[]{0x0141001C21000200L});
    public static final BitSet FOLLOW_logicalfmla_in_brule870 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_SEMI_in_brule874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_brule878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notfmla_in_logicalfmla895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andfmla_in_logicalfmla902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_notfmla915 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_OPEN_in_notfmla917 = new BitSet(new long[]{0x0141001C21000200L});
    public static final BitSet FOLLOW_logicalfmla_in_notfmla923 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_notfmla927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_guard_atom_in_andfmla942 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COMMA_in_andfmla947 = new BitSet(new long[]{0x0141001C21000200L});
    public static final BitSet FOLLOW_logicalfmla_in_andfmla951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_waitfor969 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_waitfor973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_action992 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_PLUS_in_action994 = new BitSet(new long[]{0x0100001820000000L});
    public static final BitSet FOLLOW_atom_in_action998 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_EQ_ASSGN_in_action1000 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_var_in_action1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEND_in_action1013 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_OPEN_in_action1015 = new BitSet(new long[]{0x0140000400010000L});
    public static final BitSet FOLLOW_literal_in_action1019 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_action1021 = new BitSet(new long[]{0x0020002000000020L});
    public static final BitSet FOLLOW_performative_in_action1025 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_action1027 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_pred_in_action1031 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_action1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pred_in_action1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TELL_in_performative1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERFORM_in_performative1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACHIEVE_in_performative1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classpath_in_environment1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_word_in_classpath1335 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_POINT_in_classpath1340 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_word_in_classpath1344 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_CONST_in_word1445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_word1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_agentnameterm1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_agentnameterm1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_literal1586 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_pred_in_literal1590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pred_in_literal1604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_pred1620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_pred1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_function1639 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_OPEN_in_function1644 = new BitSet(new long[]{0x0100001820110000L});
    public static final BitSet FOLLOW_terms_in_function1646 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_function1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_terms1662 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COMMA_in_terms1667 = new BitSet(new long[]{0x0100001820110000L});
    public static final BitSet FOLLOW_terms_in_terms1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_term1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringterm_in_term1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_term1706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberstring_in_atom1724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_atom1737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_in_atom1743 = new BitSet(new long[]{0x0100001820000000L});
    public static final BitSet FOLLOW_arithexpr_in_atom1747 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_CLOSE_in_atom1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLEQUOTE_in_stringterm1762 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_STRING_in_stringterm1765 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_DOUBLEQUOTE_in_stringterm1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_var1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_numberstring1798 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_NUMBER_in_numberstring1807 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_POINT_in_numberstring1817 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_NUMBER_in_numberstring1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arithexpr_in_equation1840 = new BitSet(new long[]{0x0000000004400000L});
    public static final BitSet FOLLOW_eqoper_in_equation1844 = new BitSet(new long[]{0x0100001820000000L});
    public static final BitSet FOLLOW_arithexpr_in_equation1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_eqoper1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_eqoper1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multexpr_in_arithexpr1884 = new BitSet(new long[]{0x0000010020000002L});
    public static final BitSet FOLLOW_addoper_in_arithexpr1892 = new BitSet(new long[]{0x0100001820000000L});
    public static final BitSet FOLLOW_multexpr_in_arithexpr1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_multexpr1913 = new BitSet(new long[]{0x00000000C0080002L});
    public static final BitSet FOLLOW_multoper_in_multexpr1920 = new BitSet(new long[]{0x0100001820000000L});
    public static final BitSet FOLLOW_atom_in_multexpr1924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_addoper1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_addoper1946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_multoper1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_multoper1967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOD_in_multoper1973 = new BitSet(new long[]{0x0000000000000002L});

}
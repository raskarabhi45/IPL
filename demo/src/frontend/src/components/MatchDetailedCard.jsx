import React from 'react'

const MatchDetailedCard = ({teamName,match}) => {
    if(!match) return null;

    const otherTeam=match.team1===teamName ? match.team2 : match.team1;
  return (

    <div className='MatchDetailedCard'>
    <h3>Latest Matches</h3>
    <h1> vs {otherTeam}</h1>
    <h2>{match.date}</h2>
    <h3>{match.venue}</h3>
    <h3>{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
    </div>
  )
}

export default MatchDetailedCard
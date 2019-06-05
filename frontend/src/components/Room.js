import React, {Component} from 'react'

class Room extends Component {
    render() {
        return (
            <button onClick={() => {
                alert(this.props.room.name);
                this.props.setRoom(this.props.room.id)}}> # {this.props.room.name}</button>
        )
    }
}

export default Room
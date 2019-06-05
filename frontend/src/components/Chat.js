import React, {Component} from 'react';
import MessageList from "./MessageList"
import NewRoomForm from "./NewRoomForm"
import RoomList from "./RoomList"
import SendMessageForm from "./SendMessageForm"
import '../style.css';

const tempRooms = [
    "Good Room",
    "The Best Room",
    "Omami"
];

class Chat extends Component {
    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            rooms: []
        };

        this.sendMessage = this.sendMessage.bind(this)
        this.createRoom= this.createRoom.bind(this)
    }

    sendMessage(text) {
        this.setState({
            messages: [...this.state.messages, text]
        });
    }

    createRoom(room) {
        this.setState ( {
            rooms: [...this.state.rooms, room]
        })
    }

    render() {
        return (
            <div className="main">
                 <div className="app">
                    <RoomList rooms={[...this.state.rooms, ...tempRooms]}/>
                    <MessageList
                        messages={this.state.messages}
                        sendMessage={this.sendMessage} />
                    <NewRoomForm createRoom={this.createRoom}/>
                    <SendMessageForm/>
                </div>
            </div>
        );
    }
}

export default Chat;
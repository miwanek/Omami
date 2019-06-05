import React, {Component} from 'react';
import Login from "./components/Login"
import { Redirect } from 'react-router-dom'
import Chat from "./components/Chat"
import Message from "./components/Message"
import MessageList from "./components/MessageList"
import NewRoomForm from "./components/NewRoomForm"
import RoomList from "./components/RoomList"
import SendMessageForm from "./components/SendMessageForm"
import './style.css';

const tempRooms = [
    "Good Room",
    "The Best Room",
    "Omami"
];

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            rooms: [],
            toChat: false,
            currentRoom: 0
        };

        this.sendMessage = this.sendMessage.bind(this)
        this.loadMessages = this.loadMessages.bind(this)
        this.createRoom= this.createRoom.bind(this)
        this.goToChat= this.goToChat.bind(this)
        this.setRoom = this.setRoom.bind(this)
        this.loadRooms = this.loadRooms.bind(this)
    }

    setRoom(roomId){
       this.setState( {
           currentRoom : roomId});
    }

    sendMessage(username, roomId, text) {
        this.setState({
            messages: [...this.state.messages, {username, roomId, text}]
        });
    }

    loadMessages(messageList) {
        this.setState({
            messages: messageList
        });
    }

    createRoom(room) {
        this.setState ( {
            rooms: [...this.state.rooms, room]
        })
    }

    loadRooms(rooms)
    {
        this.setState({
            rooms: rooms
        })
    }

    goToChat(toChat){
        this.setState ( {
            toChat: toChat
        })
    }
    render() {
        if(this.state.toChat === false) {
            //<Redirect to='/chat'/>

            return (
                <div className="main">
                    {<div className="Login"><Login goToChat={this.goToChat}/></div>}
                </div>
            );
        }
        if(this.state.toChat === true) {
            return (
                <div className="main">
                    {/*<div className="Login"><Login/></div>*/}
                    <div className="app">
                        <RoomList
                            loadRooms = {this.loadRooms}
                            userId = {this.state.userId}
                            rooms={[...this.state.rooms, ...tempRooms]}
                            setRoom={this.setRoom}/>
                        <MessageList
                            currentRoom = {this.state.currentRoom}
                            messages={this.state.messages}
                            loadMessages={this.loadMessages}/>
                        <NewRoomForm createRoom={this.createRoom}/>
                        <SendMessageForm
                            currentRoom = {this.state.currentRoom}
                            sendMessage={this.sendMessage}
                            loadMessages={this.loadMessages}/>
                    </div>
                </div>
            );
        }
    }
}

export default App;

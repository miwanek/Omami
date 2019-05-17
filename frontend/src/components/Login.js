import React, {Component} from 'react'
import Form from 'react-bootstrap/Form';
import '../login.css'

export default class Login extends Component {
    constructor() {
        super();

        this.state = {
            email: "",
            password: ""
        };

        this.authorize = this.authorize.bind(this)
        this.createJsonLoginForm = this.createJsonLoginForm.bind(this);
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
       
        this.setState({
            [event.target.id]: event.target.value
        });
        console.log(this.state.email)
    }

    handleSubmit = event => {
        event.preventDefault();
    }
    
     async authorize () {
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        const data = this.createJsonLoginForm(this.state.email, this.state.password);
        const options = {
            method: 'POST',
            headers,
            body: data
        }
        const request = new Request('https://omami.herokuapp.com/users/login', options);
        const response = await fetch(request);
        const status = await response.status;
         //this.props.goToChat(true);
        if(status === 200) {
            this.props.goToChat(true);
            //this.state.email = "JEST";
        }

        else if(status === 404) {
            this.state.email = "Username or password incorrect!";
        }
    }

    createJsonLoginForm(email, password){
        var json = '{"username": "' + email + '", "password": "'+ password + '"}';
        return json;
    }

    render() {
        return (
            <body className="align">
            <div className="grid align__item">
            <div className="register">
            <svg xmlns="http://www.w3.org/2000/svg" class="site__logo" width="56" height="84" viewBox="77.7 214.9 274.7 412"><defs><linearGradient id="a" x1="0%" y1="0%" y2="0%"><stop offset="0%" stop-color="#8ceabb"/><stop offset="100%" stop-color="#378f7b"/></linearGradient></defs><path fill="url(#a)" d="M215 214.9c-83.6 123.5-137.3 200.8-137.3 275.9 0 75.2 61.4 136.1 137.3 136.1s137.3-60.9 137.3-136.1c0-75.1-53.7-152.4-137.3-275.9z"/></svg>
            <h2>Sign Up</h2>
            <form action="" method="post" class="form">
            <div class="form__field">
            <input type="email" placeholder="info@mailaddress.com">
            </input>
            <div class="form__field">
          <input type="password" placeholder="••••••••••••">
          </input>
          <div class="form__field">
          <input type="submit" value="Sign Up"></input>
        </div>
        </div>
            </div>
          
            </form>
            <p>Already have an accout? <a href="#">Log in</a></p>
            </div></div>
            </body>
        );
    }
}

// export default withRouter (Login);
import React, {Component} from 'react'
import '../login/Login.css'
import Form from 'react-bootstrap/Form'

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: ""
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();
    }

    render() {
        return (
            <div className="Login">
                <label className="greeting">hi, dear</label>

                <Form onSubmit={this.handleSubmit} >
                    <Form.Label className="label">email: </Form.Label>
                    <Form.Group controlId="email" bsSize="large">
                        <Form.Control className="input"
                                      autoFocus
                                      type="email"
                                      value={this.state.email}
                                      onChange={this.handleChange}
                        />
                    </Form.Group>
                    <Form.Label className="label">password: </Form.Label>
                    <Form.Group controlId="password" bsSize="large">
                        <Form.Control className="input"
                                      value={this.state.password}
                                      onChange={this.handleChange}
                                      type="password"
                        />
                    </Form.Group>

                    <button type="submit" block class="login-btn"
                            disabled={!this.validateForm()}
                    >
                        Login
                    </button>
                </Form>


            </div>

        );
    }
}
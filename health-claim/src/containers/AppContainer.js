import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import App from "../components/Core/App";
import { logoutCall } from "../actions/login";

const mapStateToProps = (state, props) => ({
  ...props,
  loggedIn: state.verifySession.success
});

const mapDispatchToProps = (dispatch, props) => ({
  ...props,
  logout: () => dispatch(logoutCall())
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);

package at.htl_villach.scrumable.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.htl_villach.scrumable.R;

public class RegisterFragment extends Fragment {

    private Pattern usernamePattern;
    private Pattern birthdayPattern;
    private Pattern passwordPattern;
    private Pattern confPasswordPattern;

    private Matcher usernameMatcher;
    private Matcher birthdayMatcher;
    private Matcher passwordMatcher;
    private Matcher confPasswordMatcher;

    private static final String USERNAME_PATTERN = "(^[A-Z]\\w{5,})";
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)";
    private static final String PASSWORD_PATTERN = "(([a-z]|[A-Z]|[0-9]){8,})";

    EditText etUsername;
    EditText etBirthday;
    EditText etPassword;
    EditText etConPassword;

    Button btnRegister;

    TextView tvDebugUsername;
    TextView tvDebugBirthday;
    TextView tvDebugPassword;
    TextView tvDebugConfPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        birthdayPattern = Pattern.compile(DATE_PATTERN);
        usernamePattern = Pattern.compile(USERNAME_PATTERN);
        passwordPattern = Pattern.compile(PASSWORD_PATTERN);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etUsername = view.findViewById(R.id.et_username);
        etBirthday = view.findViewById(R.id.tv_birthday);
        etPassword = view.findViewById(R.id.et_password);
        etConPassword = view.findViewById(R.id.et_repassword);

        btnRegister = view.findViewById(R.id.btn_register);


        tvDebugUsername = view.findViewById(R.id.tvDebugUsername);
        tvDebugBirthday = view.findViewById(R.id.tvDebugBirthday);
        tvDebugPassword = view.findViewById(R.id.tvDebugPassword);
        tvDebugConfPassword = view.findViewById(R.id.tvDebugConfPassword);

        // make all find view by ids here

        addEventListeners();

        return view;
    }

    public void addEventListeners() {
        btnRegister.setOnClickListener(OnClickListenerRegister);
        // andoroidElement.setOn
    }

    // Listeners like this
    private View.OnClickListener OnClickListenerRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            birthdayMatcher = birthdayPattern.matcher(etBirthday.getText().toString());
            usernameMatcher = usernamePattern.matcher(etUsername.getText().toString());
            passwordMatcher = passwordPattern.matcher(etPassword.getText().toString());
           // confPasswordMatcher = confPasswordPattern.matcher(etConPassword.getText().toString());


            if (usernameMatcher.matches()) {
                tvDebugUsername.setText("Username is correct");
            } else {
                tvDebugUsername.setText("Username is not correct");
            }
            if (birthdayMatcher.matches()) {
                tvDebugBirthday.setText("Date is correct");
            } else {
                tvDebugBirthday.setText("Date is not correct");
            }
            if (passwordMatcher.matches()) {
                tvDebugPassword.setText("Password is correct");
            } else {
                tvDebugPassword.setText("Password is not correct");
            }
            if (etPassword.getText().toString() == etConPassword.getText().toString()) {
                tvDebugConfPassword.setText("Confirm Password is not correct");
            }else{
                tvDebugConfPassword.setText("Conf Password is correct");
            }
        }
    };
}


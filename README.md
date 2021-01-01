# saf_parade_state

A simple parade state compiler/generator crafted specifically with a certain format which consists of:

1. 5 platoons
    - Platoon 1
    - Platoon 2
    - Platoon 3
    - Platoon 4
    - Coy HQ
2. Format:
    - ```
        {date}
        {paradeType}

        {platoon} TOTAL STRENGTH: {platoonCurrentStrength}/{platoonTotalStrength}

        {section1}: {section1CurrentStrength}/{section1TotalStrength}
        {section2}: {section2CurrentStrength}/{section2TotalStrength}
        {section3}: {section3CurrentStrength}/{section3TotalStrength}
        {section4}: {section4CurrentStrength}/{section4TotalStrength}
        {section5}: {section5CurrentStrength}/{section5TotalStrength}

        Reporting Sick:
        {section1}:
        {section1ReportingSickPersonnels}

        {section2}:
        {section2ReportingSickPersonnels}

        {section3}:
        {section3ReportingSickPersonnels}

        {section4}:
        {section4ReportingSickPersonnels}

        {section5}:
        {section5ReportingSickPersonnels}

        Medical Status:
        {section1}:
        {section1MedicalStatusPersonnels}

        {section2}:
        {section2MedicalStatusPersonnels}

        {section3}:
        {section3MedicalStatusPersonnels}

        {section4}:
        {section4MedicalStatusPersonnels}

        {section5}:
        {section5MedicalStatusPersonnels}

        Medical Appointments:
        {section1}:
        {section1MedicalAppointmentsPersonnels}

        {section2}:
        {section2MedicalAppointmentsPersonnels}

        {section3}:
        {section3MedicalAppointmentsPersonnels}

        {section4}:
        {section4MedicalAppointmentsPersonnels}

        {section5}:
        {section5MedicalAppointmentsPersonnels}

        Others:
        {section1}:
        {section1Others}

        {section2}:
        {section2Others}

        {section3}:
        {section3Others}

        {section4}:
        {section4Others}

        {section5}:
        {section5Others}
        ```

## Notes before personalising

1) Dates used are in the format: *ddMMyy*

2) Once platoon parade states are entered, they are saved in files of name format: *date_platoon_paradeType*

3) Many errors may not be caught

## Personalising

Clone me first!

### I have more/fewer platoons

1) Edit number of `MaterialCardView` in both `fragment_first_parade_tab.xml` and `fragment_last_parade_tab.xml` in `res -> layout` to suit your conditions

2) Edit `FirstParadeTab.kt` and `LastParadeTab.kt` in `com.beebeeoii.paradestate.fragments` accordingly

### My parade state format is different

1) Navigate to `ParadeStateInfoPuller.kt` in `com.beebeeoii.paradestate.helper` and specify how respective information should be pulled from your parade state format

### My company parade state format is different

1) Generation of company parade state from your platoon parade states is done by `CompanyParadeStateFormatter.kt` in `com.beebeeoii.paradestate.helper`
2) Edit accordingly

 class Loop {

    int contador1 = 1;
    int contador2 = 2;
    int contador3 = 3;

    

    void repetir(int contador1) {


        switch (contador1) {
            case 1:
                System.out.println("Case 1");
                
                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            default:
                System.out.println("Default case");
        }

    }
        void repetir2(int contador2) {
            switch (contador2) {
                case 1:
                    System.out.println("Case 1");
                    break;
                case 2:
                    System.out.println("Case 2");
                    break;
                case 3:
                    System.out.println("Case 3");
                    break;
                default:
                    System.out.println("Default case");
            }

        }

            void reoutur3(int contador3) {
                switch (contador3) {
                    case 1:
                        System.out.println("Case 1");
                        break;
                    case 2:
                        System.out.println("Case 2");
                        break;
                    case 3:
                        System.out.println("Case 3");
                        App.main(null);
                        break;
                    default:
                        System.out.println("Default case");
                }
            }
        }
            
    


#include<stdio.h>
#include<string.h>
#include<stdlib.h>
char** scanfInfo(int* count) {
    char brand[10];
    int ram;
    float price;
    int choiceChip;
    char choiceYN;
    char info[200];
    char** listComputer = (char**) malloc(100 * sizeof(char*));

    int i = 0;
    while(1) {
        printf("Moi nhap hang cua may tinh: ");
        scanf("%s", brand);
        printf("Moi nhap ram cua may tinh: ");
        scanf("%d",&ram);
        printf("Moi nhap chip cua may tinh:\n1:i3\n2:i5\n3:i7 \n");
        scanf("%d",&choiceChip);
        printf("Moi nhap gia cua may tinh: \n");
        scanf("%f",&price);

        if (choiceChip == 1) {
            sprintf(info, "- %s - %d - %f","i3",ram,price);
        } else if (choiceChip == 2)
        {
            sprintf(info, "- %s - %d - %f","i5",ram,price);  
        } else if (choiceChip == 3)
        {
            sprintf(info, "- %s - %d - %f","i7",ram,price);
        }
        char* infoComputer = (char*) malloc((strlen(info)+1)*sizeof(char));

        strcpy(infoComputer,info);
        listComputer[i] = infoComputer;
        i++;
        printf("Du lieu da duoc them vao list\n");
        printf("Ban co muon nhap them thong tin cua may tinh khac khong? Nhan y de tiep tuc, n de dung lai: ");
        getchar();
        scanf("%c",&choiceYN);
        printf("day la choice:%c",choiceYN);
        if(choiceYN == 'y') {
            continue;
        }
        else if (choiceYN == 'n')
        {
            break;
        }
      
    }
    *count = i;
    return listComputer;
}
void setCode(char** listComputer,int count) {
    char code[20];
    char line[100];
    char result[1000];
    char** finalResult = (char**) malloc((count+1)*sizeof(char*));
    for (int i = 0; i < count; i++)
    {
        printf("Moi ban nhap ma cho may tinh:%s",listComputer[i]);
        scanf("%s",code);
        sprintf(line,"%d - %s - %s",i,code,listComputer[i]);
        char* charLine = (char*) malloc((strlen(line)+1)*sizeof(char));
        strcpy(charLine,line);
        finalResult[i] = charLine;
    }
    printf("Stt - Ma - Chip - Ram - Gia\n");
    for (int i = 0; i < count; i++)
    {
        printf("%s\n",finalResult[i]);
        free(finalResult[i]);
        
    } 
}
void sortByPrice(char** listComputer,float* listPrice, int count) {

}
int main() {
    int count;
    char** list = scanfInfo(&count);
    for (int i = 0; i < count; i++) {
        printf("%s\n", list[i]);
    }
    setCode(list,count);
    sortByPrice(list)
 free(list);
    return 0;
}